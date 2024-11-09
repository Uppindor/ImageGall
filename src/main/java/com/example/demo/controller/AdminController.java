package com.example.demo.controller;

import com.example.demo.models.Event;
import com.example.demo.models.Hall;
import com.example.demo.models.Image;
import com.example.demo.service.EventService;
import com.example.demo.service.HallService;
import com.example.demo.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Controller
public class AdminController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private HallService hallService;

    @Autowired
    private EventService eventService;
    @Value("${uploadDir}")
    private String uploadDir;



    @PostMapping("/admin_panel_test_image")
    public String addImageAdmin(@ModelAttribute Image image, @RequestParam("image") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        byte[] fileImage =  file.getInputStream().readAllBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(fileImage);

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        String fileName = uploadDir + hexString.toString();

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        image.setImageHref(fileName);
        fileOutputStream.write(file.getInputStream().readAllBytes());

        System.out.println(image.getDescription());


        System.out.println(image.getName());


        imageService.saveImage(image);

        return "redirect:/admin_panel_test";
    }

    @PostMapping("/admin_panel_test/hall")
    public String addHallAdmin(@ModelAttribute Hall hall) throws IOException
    {
        hallService.saveHall(hall);
        return "redirect:/admin_panel_test";
    }

    @PostMapping("/admin_panel_test_event")
    public String addEventAdmin(@ModelAttribute Event event) throws IOException
    {
        eventService.saveEvent(event);
        return "redirect:/admin_panel_test";
    }

    @GetMapping(value = "/admin_panel_test/delete/{id}")
    public String deleteImage(@PathVariable(name="id")Long id) {

        imageService.deleteImage(id);
        // Возвращаем ошибку доступа, если у пользователя нет административных прав
        return "redirect:/admin_panel_test";
    }

    @GetMapping(value = "/admin_panel_test/image/{id}", produces = "image/bmp")
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {
        Image image = imageService.getImage(id);
        InputStream in = new FileInputStream(image.getImageHref());
        return IOUtils.toByteArray(in);
    }
    @GetMapping("/admin_panel_test")
    public String images(Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        if (userDetails != null) {
            model.addAttribute("isAuthenticated", true); // Пользователь аутентифицирован
        } else {
            model.addAttribute("isAuthenticated", false); // Пользователь не аутентифицирован
        }
        imageService.getAll();
        model.addAttribute("halls", hallService.getAll());
        model.addAttribute("images", imageService.getAll());
        model.addAttribute("events", eventService.getAll());
        return "admin_panel_test";
    }

    @GetMapping(value = "/admin_panel_test/hall/delete/{id}")
    public String deleteHall(@PathVariable(name="id")Long id) {
        hallService.deleteHall(id);
        // Возвращаем ошибку доступа, если у пользователя нет административных прав
        return "redirect:/admin_panel_test";
    }


    @GetMapping(value = "/admin_panel_test/event/delete/{id}")
    public String deleteEvent(@PathVariable(name="id")Long id) {
        eventService.deleteEvent(id);
        // Возвращаем ошибку доступа, если у пользователя нет административных прав
        return "redirect:/admin_panel_test";
    }



}
