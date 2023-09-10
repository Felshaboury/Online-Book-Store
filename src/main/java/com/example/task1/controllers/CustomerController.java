package com.example.task1.controllers;
import com.example.task1.entities.User;
import com.example.task1.entities.Book;
import com.example.task1.entities.Orders;
import com.example.task1.model.request.AuthenticationRequest;
import com.example.task1.model.request.RegisterRequest;
import com.example.task1.model.response.AuthenticationResponse;
import com.example.task1.services.AuthenticationService;
import com.example.task1.services.BookService;
import com.example.task1.services.OrderService;
import com.example.task1.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @RolesAllowed("USER")
    @GetMapping("/user/{userId}/owned-books")
    public ResponseEntity<List<Book>> getOwnedBooks(@PathVariable Long userId) {
        List<Book> ownedBooks = orderService.getOwnedBooksForUser(userId);
        return ResponseEntity.ok(ownedBooks);
    }

}
