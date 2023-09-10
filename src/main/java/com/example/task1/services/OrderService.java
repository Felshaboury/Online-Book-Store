package com.example.task1.services;

import com.example.task1.repo.OrderRepo;
import com.example.task1.entities.Book;
import com.example.task1.entities.Orders;
import com.example.task1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    public Orders createOrder(User user, List<Book> books) {
        Orders order = new Orders(user, books);

        double totalPrice = books.stream().mapToDouble(Book::getPrice).sum();
        order.setTotalPrice(totalPrice);

        order = orderRepo.save(order);

        return order;
    }
    public List<Book> getOwnedBooksForUser(Long userId) {
        List<Orders> userOrders = orderRepo.findByUserId(userId);
        List<Book> ownedBooks = new ArrayList<>();

        for (Orders order : userOrders) {
            ownedBooks.addAll(order.getBooks());
        }

        return ownedBooks;
    }
public List<Orders> getOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }

}

