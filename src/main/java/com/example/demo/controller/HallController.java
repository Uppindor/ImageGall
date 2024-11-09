package com.example.demo.controller;

import com.example.demo.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping("/hall")
    public String halls(Model model,@AuthenticationPrincipal UserDetails userDetails)
    {
        if (userDetails != null) {
            model.addAttribute("isAuthenticated", true); // Пользователь аутентифицирован
        } else {
            model.addAttribute("isAuthenticated", false); // Пользователь не аутентифицирован
        }
        hallService.getAll();
        model.addAttribute("halls", hallService.getAll());
        return "hall";
    }



}
