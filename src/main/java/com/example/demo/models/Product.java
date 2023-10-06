package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data // getter setter
@Entity
@Table(name = "products")
@NoArgsConstructor // конструктор не по умолчанию
@AllArgsConstructor // конструктор для всех полей
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "city")
    private String city;

    @Column(name = "author")
    private String author;


    // ALL чтобы удаляло абсолютно все фото в связке при удалении этого товара,
    // + при сохранении товара список фотографий будет с ним сохранятся и сущности
    // LAZY не нужно подгружать остальные фото при подгрузке 1 товара(все его фото)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "product") // товар связанный с фото будет FK
    private List<Image> images = new ArrayList<>();
    private Long previewImageId; // превьюшка ли это фото на главной странице
    private LocalDateTime dateOfCreated; // когда был создан товар

    @PrePersist // инициализация бина в спринге
    private void init () {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Image image) {
        image.setProduct(this); // установили текущий товар
        images.add(image);
    }
}