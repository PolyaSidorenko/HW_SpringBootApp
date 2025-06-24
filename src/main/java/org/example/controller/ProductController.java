package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dao.ProductService;
import org.example.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер обрабтывает HTTP-запросы для отображения, создания, редактирования, обновления и удаления продуктов
 */
@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "allProducts";
    }

    @GetMapping("/new")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.getById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.delete(productService.getById(id));
        return "redirect:/products";
    }
}
