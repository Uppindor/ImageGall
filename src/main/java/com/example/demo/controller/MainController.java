package com.example.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("isAuthenticated", true); // Пользователь аутентифицирован
        } else {
            model.addAttribute("isAuthenticated", false); // Пользователь не аутентифицирован
        }return "home";
    }
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails,Model model) {
        if (userDetails != null) {
            model.addAttribute("isAuthenticated", true); // Пользователь аутентифицирован
        } else {
            model.addAttribute("isAuthenticated", false); // Пользователь не аутентифицирован
        }
        return "home";
    }




}
