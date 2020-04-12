package com.example.HaweliKitchen.repository;

import com.example.HaweliKitchen.model.BillItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemsRepository  extends JpaRepository<BillItems,Integer> {
}
