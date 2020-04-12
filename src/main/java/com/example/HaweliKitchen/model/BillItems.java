package com.example.HaweliKitchen.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "billitems")
public class BillItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int paymentId;
    public int custId;
    public int orderId;
    public String paymentTime;
    public float billAmount;
    public String paymentType;

    public BillItems(int custId, int orderId, String paymentTime, float billAmount, String paymentType) {
        this.custId = custId;
        this.orderId = orderId;
        this.paymentTime = paymentTime;
        this.billAmount = billAmount;
        this.paymentType = paymentType;
    }
}
