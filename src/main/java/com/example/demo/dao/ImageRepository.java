package com.example.demo.dao;

import com.example.demo.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

}