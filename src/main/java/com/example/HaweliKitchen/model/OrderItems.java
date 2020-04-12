package com.example.HaweliKitchen.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@ToString
@Entity
@Table(name = "ordereditems")
public class OrderItems {
    @Id
    public int orderId;
    public String foodItems;

    public OrderItems(int orderId, String foodItems) {
        this.orderId = orderId;
        this.foodItems = foodItems;
    }
}
