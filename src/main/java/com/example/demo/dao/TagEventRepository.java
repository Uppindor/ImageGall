package com.example.demo.dao;

import com.example.demo.models.TagEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagEventRepository extends JpaRepository<TagEvent,Long> {
}
