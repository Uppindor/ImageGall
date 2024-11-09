package com.example.demo.controller;

import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController  {


    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (request.isUserInRole("ROLE_AUTHOR")) {
               return "redirect:/author_panel";
            }
            if (request.isUserInRole("ROLE_ADMIN")) {
                return "redirect:/admin_panel_test";
            }
            if (request.isUserInRole("ROLE_USER")) {
                return "redirect:/home";
            }
        }
        return "login";
    }
    @GetMapping("/unauthenticated")
    public String unauthenticated() {
        return "unauthenticated"; // возвращает шаблон unauthenticated.html
    }
    @GetMapping("/auth/status")
    public String getAuthStatus(Authentication authentication) {
        return authentication != null ? "authenticated" : "unauthenticated";
    }


}