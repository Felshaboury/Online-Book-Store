package com.example.task1.repo;

import com.example.task1.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long userId);

}
