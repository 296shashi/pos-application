package com.example.HaweliKitchen.repository;

import com.example.HaweliKitchen.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailRepository extends JpaRepository<OrderItems,Integer> {
}
