package com.example.HaweliKitchen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.HaweliKitchen.OrderStatusEnum;
import com.example.HaweliKitchen.model.BillItems;
import com.example.HaweliKitchen.model.FoodItems;
import com.example.HaweliKitchen.model.OrderItems;
import com.example.HaweliKitchen.model.Orders;
import com.example.HaweliKitchen.repository.BillItemsRepository;
import com.example.HaweliKitchen.repository.CurrentOrdersRepository;
import com.example.HaweliKitchen.repository.OrdersDetailRepository;

import java.util.List;

public class DashBoard {

    @Autowired
    private CurrentOrdersRepository cor;
    @Autowired
    private OrdersDetailRepository odr;
    @Autowired
    private BillItemsRepository bir;

    public String prepareDashboard()
    {
//        List<Orders> orders = cor.findAll();
//        List<OrderItems> orderItems = odr.findAll();
//        List<BillItems> billItems = bir.findAll();

     return "DashBoardpage";
    }


}
