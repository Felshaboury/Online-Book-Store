package com.example.task1.repo;
import com.example.task1.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {

    // Add custom query methods if needed
}

