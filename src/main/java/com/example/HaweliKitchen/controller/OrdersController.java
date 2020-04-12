package com.example.HaweliKitchen.controller;


import com.example.HaweliKitchen.model.BillItems;
import com.example.HaweliKitchen.model.FoodItems;
import com.example.HaweliKitchen.model.Orders;
import com.example.HaweliKitchen.repository.CurrentOrdersRepository;
import com.example.HaweliKitchen.repository.OrdersDetailRepository;
import com.example.HaweliKitchen.services.OrderProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrdersController {
    @Autowired
    public OrderProcessing op;
    @Autowired
    private CurrentOrdersRepository cor;

    @RequestMapping(value = "/order/place",method = RequestMethod.POST )
    @ResponseBody
    public String receiveOrders()
    {
        List<FoodItems> fItemsList = new ArrayList<>() ;
        fItemsList.add(new FoodItems("noodle",1,10));
        Integer orderId=op.acceptingOrder(fItemsList,123);

        if(null == orderId){
            return "Failed to take orders,try after sometime";
        }
        return "Orders received successfully with order id [" + orderId + "]";
    }

    @RequestMapping(value = "/order/status/{id}",method = RequestMethod.GET )
    @ResponseBody
    public String checkOrderStatus(@PathVariable Integer id) throws IOException {
      return op.orderStatus(id, true);
    }


    @RequestMapping(value = "/order/update/{id}",method = RequestMethod.POST )
    @ResponseBody
    public String updateOrderStatus(@PathVariable(value = "id") Integer id) throws IOException {
        return op.orderStatus(id, false);
    }

    @RequestMapping(value = "/order/dashboard",method = RequestMethod.GET )
    public String showDashBoard() {

        return "Dashboardpage";
    }
}
