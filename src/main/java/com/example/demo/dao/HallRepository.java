package com.example.demo.dao;

import com.example.demo.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall,Long> {
}
