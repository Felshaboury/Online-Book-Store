package com.example.task1.controllers;

import com.example.task1.entities.Book;
import com.example.task1.entities.Orders;
import com.example.task1.entities.User;
import com.example.task1.model.request.PostOrderRequest;
import com.example.task1.services.BookService;
import com.example.task1.services.OrderService;
import com.example.task1.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @RolesAllowed("USER")
    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody PostOrderRequest bookIds) {
        String customerUsername = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUserByUsername(customerUsername);

        List<Book> selectedBooks = new ArrayList<>();
        double totalPrice = 0.0;

        for (Long bookId : bookIds.getBookIds()) {
            Book book = bookService.getBookById(bookId);
            selectedBooks.add(book);
            totalPrice += book.getPrice();
        }

        Orders order = orderService.createOrder(user, selectedBooks);

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("totalPrice", totalPrice);

        return ResponseEntity.ok(response);
    }

}
