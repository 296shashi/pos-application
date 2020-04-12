package com.example.HaweliKitchen.repository;

import com.example.HaweliKitchen.model.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItems,Integer> {
}
