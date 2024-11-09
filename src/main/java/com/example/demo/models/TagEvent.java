package com.example.demo.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tag_event")
@Data
public class TagEvent extends Tag {

@Column(name = "name_genre")
    private String name_genre;

    public String getName_genre() {
        return name_genre;
    }

    public void setName_genre(String name_genre) {
        this.name_genre = name_genre;
    }
}
