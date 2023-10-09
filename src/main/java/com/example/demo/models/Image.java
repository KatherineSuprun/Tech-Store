package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName; // передавать картинки

    @Column(name = "size")
    private Long size; // размер фото

    @Column(name = "contentType")
    private String contentType; // расширение файла

    @Column(name = "isPreviewImage")
    private boolean isPreviewImage; // отображение(какого) фото будет на главной странице

    @Lob
    @Column(name = "bytes")
    private byte[] bytes;

    // у одного товара имеется много фотографий, у нескольких фото имеется один товар
    // сторона товара - OneToMany, сторона фото ManyToOne
    // REFRESH как повлияет действие с фото на сущность товара , рефреш - обновиться
    // товар подгружаем не сразу(LAZY) , если (EAGER) подгружая фото подгружаем все остальные
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;

}
