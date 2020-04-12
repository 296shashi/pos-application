package com.example.HaweliKitchen.exceptions;

public class FoodMenuNotFoundExceptions extends RuntimeException {
    public FoodMenuNotFoundExceptions(int id) {
        super("Could not find employee " + id);
    }
}
