package com.example.HaweliKitchen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int orderId;
    public String orderTime;
    public int custId;
    public String pickupTime;
    public String OrderStatus="Pending";

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public Orders(String orderTime, int custId, String pickupTime) {
        this.orderTime = orderTime;
        this.custId = custId;
        this.pickupTime = pickupTime;
    }

    public Orders(int orderId, String orderTime, int custId, String pickupTime) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.custId = custId;
        this.pickupTime = pickupTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
}

