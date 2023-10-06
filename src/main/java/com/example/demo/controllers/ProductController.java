package com.example.demo.controllers;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Controller
@RequiredArgsConstructor // заменяем конструктор

public class ProductController {

    // инверсия управления, сразу же при
    // создании компонента ProductController добавит контекст сервиса
    private final ProductService productService;

    @GetMapping("/")
    // передаем список товаров с контроллера шаблонизатору
    public String products(@RequestParam(name = "title", required = false)String title, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Product product) throws IOException {
        productService.saveProduct(product, file1, file2,file3);
        return "redirect:/"; // редирект на главную страницу, появится товар
    }

    // принимаем id товара который нужно удалить
    @Modifying
    @PostMapping("/product/delete/{id}") // id будет преобразовываться в тип long
    public String deleteProduct(@PathVariable Long id) { // как получить эту переменную
    productService.deleteProduct(id);
    return "redirect:/"; // удаление товара и переход на главную страницу
    }
    // просмотр подробной инфы о каждом товаре

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) { // Модель для передачи данных
    // товар с этим айдишником передаю в модель и отображаю
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }
}