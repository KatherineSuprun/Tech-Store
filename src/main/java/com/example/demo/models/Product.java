package com.example.demo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    // ALL чтобы удаляло абсолютно все фото в связке при удалении этого товара,
    // + при сохранении товара список фотографий будет с ним сохранятся и сущности
    // LAZY не нужно подгружать остальные фото при подгрузке 1 товара(все его фото)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
    mappedBy = "product") // товар связанный с фото будет FK
    private List<Image> images = new ArrayList<>();
    private Long previewImageId; // превьюшка ли это фото на главной странице
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)// рефреш - при удалении
    // товара чтоб не снесло юзера, лейзи чтоб не подгружать имя автора товара при загрузке товара

    @JoinColumn // FK product with user
    private User user;
    private LocalDateTime dateOfCreated; // когда был создан товар

   // &&&&&&&&&&&&&&&&&&&&??????? @PrePersist // инициализация бина в спринге
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Image image) {
        image.setProduct(this); // установили текущий товар
        images.add(image);
    }
}