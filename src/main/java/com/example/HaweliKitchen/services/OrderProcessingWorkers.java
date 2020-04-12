package com.example.HaweliKitchen.services;

import com.example.HaweliKitchen.model.Orders;
import com.example.HaweliKitchen.repository.CurrentOrdersRepository;
import com.example.HaweliKitchen.repository.OrdersDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OrderProcessingWorkers implements Runnable {

    @Autowired
    private OrdersDetailRepository odr;
    @Autowired
    private CurrentOrdersRepository cor;

    Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        processOrderByWkrThread();

    }
    private void processOrderByWkrThread() {
        try {
            //To Do for future task

            System.out.println(this.getOrderId());
            System.out.println("i am here:"+ cor.findById(orderId));

        }catch (Exception e){

        }
    }
}
