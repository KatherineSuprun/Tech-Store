package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // является Компонентом
@Slf4j // логирование
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    // добавление товара
  //  private List<Product> products = new ArrayList<>();

   /* { // у каждого товара свой идентификатор, ставлю инкремент
        products.add(new Product("PlayStation 5", "Lightning speed\n" +
                "Haptic feedback\n" +
                "3D audio\n" +
                "HyperMotionV", 21000, "Kyiv", "PlayStation" ));
        products.add(new Product( "Samsung A50", "RAM 6 GB\n" +
                "built-in 128 GB\n" +
                "camera 25 MP\n" +
                "wide-angle 8 MP", 6000, "Lviv", "Samsung" ));
        products.add(new Product("Marshall Major IV", "Frequency range 20-20000 Hz\n" +
                "Sensitivity 98 dB\n" +
                "Weight 240\n" +
                "Charging time 3 hours\n" +
                "Operating time, 3 hours", 6700, "Odessa", "Marshall"));
    }*/
    // просмотр всех товаров
    public List <Product> listProducts (String title) {
        if (title != null) productRepository.findByTitle(title); // метод благодаря jpa
        return productRepository.findAll();
    }

    // сохранение
    public void saveProduct(Product product) {
        log.info("Saving new {}", product); // подставит строковое представление продукта
        productRepository.save(product);
    }

    // удаление товара по id
    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }

    // найти товар по id, если нету - вернуть null
    public Product getProductById(Long id) { // если товара с таким id нету - null
        return productRepository.findById(id).orElseThrow(null);
    }
}
