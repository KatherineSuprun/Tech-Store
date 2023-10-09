package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service // является Компонентом
@Slf4j // логирование
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    // добавление товара
    private List<Product> products = new ArrayList<>();

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
    public List<Product> listProducts(String title) {
        if (title != null) productRepository.findByTitle(title); // метод благодаря jpa
        return productRepository.findAll();
    }

    // сохранение, функция добавления 3-ёх картинок к товару
    public void saveProduct(Product product, MultipartFile file1,
                            MultipartFile file2,MultipartFile file3) throws IOException{
        Image image1;
        Image image2;
        Image image3;
        // если размер фото не 0 (тоесть её нет), то image преобразовывем из MultipartFile в фото
        if(file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true); // если картинка первая, то она - превью
            product.addImageToProduct(image1);
        }
        if(file2.getSize() != 0){
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if(file3.getSize() != 0){
            image3 = toImageEntity(file1);
            product.addImageToProduct(image3);
        }

        log.info("Saving new Product.Title : {}; Author: {}",
                product.getTitle(), product.getAuthor()); // подставит строковое представление продукта
        Product productFromDb = productRepository.save(product);// получаем новый товар
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product); // обновление и сохранение товара с айди првеью
    }


    // метод для преобразрования файла в картинку
    private Image toImageEntity(MultipartFile file) throws IOException {
      Image image = new Image();
      image.setName(file.getName()); // назначаем имя из файла
      image.setOriginalFileName(file.getOriginalFilename()); // назначаем оригинальное имя
      image.setContentType(file.getContentType());
      image.setSize(file.getSize());
      image.setBytes(file.getBytes());
      return image;
    }

    // удаление товара по id
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // найти товар по id, если нету - вернуть null
    public Product getProductById(Long id) { // если товара с таким id нету - null
        for (Product product : products) {
            if (product.getId().equals(id))
                return product;
        }
        return null;
    }
}
