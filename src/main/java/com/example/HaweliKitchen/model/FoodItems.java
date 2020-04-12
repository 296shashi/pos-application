package com.example.HaweliKitchen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "fooditems")
public class FoodItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int foodId;
    public String foodName;
    public int  quantity;
    public float itemPrice;

    public FoodItems(String foodName, int quantity, float itemPrice) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
}
