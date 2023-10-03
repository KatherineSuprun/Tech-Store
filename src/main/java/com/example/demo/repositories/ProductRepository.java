package com.example.demo.repositories;

import com.example.demo.controllers.ProductController;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

// JpaRepository вместо ДАО класса ( возможность CRUD методов)
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // вернет всю продукцию по названию
    List<Product> findByTitle(String title);

}
