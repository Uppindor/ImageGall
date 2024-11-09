package com.example.demo.controller;

import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EventController {


    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public String events(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("isAuthenticated", false);
        } eventService.getAll();
        model.addAttribute("events", eventService.getAll());
        return "event";
    }



}
