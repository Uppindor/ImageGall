package com.example.demo.controller;


import com.example.demo.models.Image;
import com.example.demo.models.ImageData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${uploadDir}")
    private String uploadDir;
    @GetMapping("/addImg")
    public String img()
    {
        return "addImg";
    }

    @PostMapping("/addImg")
    public String addImg(@ModelAttribute Image image, @RequestParam("image") MultipartFile file) throws IOException, NoSuchAlgorithmException {
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

        return "addImg";
    }

    @GetMapping(value = "/image/{id}", produces = "image/bmp")
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {
        Image image = imageService.getImage(id);
        InputStream in = new FileInputStream(image.getImageHref());
        return IOUtils.toByteArray(in);
    }


    @GetMapping("/images")
    public String images(Model model, @AuthenticationPrincipal UserDetails userDetails)
    { if (userDetails != null) {
        model.addAttribute("isAuthenticated", true); // Пользователь аутентифицирован
    } else {
        model.addAttribute("isAuthenticated", false); // Пользователь не аутентифицирован
    }
        imageService.getAll();
        model.addAttribute("images", imageService.getAll());
        return "img";
    }




}
