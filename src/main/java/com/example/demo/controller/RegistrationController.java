package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.models.enums.Role;
import com.example.demo.service.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private CustomUserService userService;

    public RegistrationController(CustomUserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")
                                      UserRegistrationDto registrationDto) {
        try {
            userService.save(registrationDto);
        }catch(Exception e)
        {
            System.out.println(e);
            return "redirect:/registration?email_invalid";
        }
        return "redirect:/registration?success";
    }
}