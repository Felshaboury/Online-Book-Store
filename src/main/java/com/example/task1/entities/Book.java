package com.example.task1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long bookId;

        private String title;
        private double price;
        private String author;

        @JsonIgnore
        @ManyToMany(mappedBy = "books")
        private List<Orders> order;
}
