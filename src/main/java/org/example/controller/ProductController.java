package org.example.controller;

import org.example.dao.ProductDAO;
import org.example.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productDAO.getAll());
        return "allProducts";
    }

    @GetMapping("/new")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productDAO.create(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = productDAO.getById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productDAO.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        Product product = productDAO.getById(id);
        if (product != null) {
            productDAO.delete(product);
        }
        return "redirect:/products";
    }
}
