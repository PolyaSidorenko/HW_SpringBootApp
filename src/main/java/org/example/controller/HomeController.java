package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер главной страницы приложения,
 * обрабатывает корневой маршрут "/" и перенаправляет пользователя
 * на список всех товаров по адресу "/products"
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        return "redirect:/products";
    }
}
