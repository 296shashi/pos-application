package com.example.HaweliKitchen.services;

import com.example.HaweliKitchen.OrderStatusEnum;
import com.example.HaweliKitchen.model.BillItems;
import com.example.HaweliKitchen.model.FoodItems;
import com.example.HaweliKitchen.model.OrderItems;
import com.example.HaweliKitchen.model.Orders;
import com.example.HaweliKitchen.repository.BillItemsRepository;
import com.example.HaweliKitchen.repository.CurrentOrdersRepository;
import com.example.HaweliKitchen.repository.OrdersDetailRepository;
import com.example.HaweliKitchen.utils.Initialization;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.impl.client.HttpClients;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class OrderProcessing {
    @Autowired
    private CurrentOrdersRepository cor;
    @Autowired
    private OrdersDetailRepository odr;
    @Autowired
    private BillItemsRepository bir;
    @Autowired
    private OrderProcessingWorkers workers;
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public int acceptingOrder(List<FoodItems> fItems, int custId) {
        Integer orderId = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String itemDetails = mapper.writeValueAsString(fItems);
            int orderUIID = ThreadLocalRandom.current().nextInt();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Orders orders = new Orders(dtf.format(now), custId, dtf.format(now));
            orders = cor.save(orders);
            orderId = orders.getOrderId();
            OrderItems orderItems = new OrderItems(orderId, itemDetails);
            try {
                odr.save(orderItems);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (orderId != null) {
                BillItems billItems = new BillItems(custId, orderId, dtf.format(now), 123, "UPI");
                bir.save(billItems);
                workers.setOrderId(orderId);
                System.out.println(cor.findById(orderId));
                ExecutorService executorService = Initialization.getExecutor();
                executorService.execute(workers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public String orderStatus(Integer orderId, boolean isStatusCall) {
        Optional<Orders> optionalOrders = cor.findById(orderId);
        Orders orders = null;
        if (optionalOrders.isPresent()) {
            orders = optionalOrders.get();
            if (isStatusCall) {
                return orders.getOrderStatus();
            }
        }
        String status = getStatusFromStatusAPI(orderId);
        if (OrderStatusEnum.COMPLETE.equals(status)) {
            return status;
        }
        orders.setOrderStatus(OrderStatusEnum.COMPLETE.name());
        orders = cor.saveAndFlush(orders);
        return orders.getOrderStatus();
    }

    private String getStatusFromStatusAPI(Integer orderId) {
        StringBuilder url = new StringBuilder();
        int responseCode = 0;
        url.append("http://localhost:8080/order/status/").append(orderId);
        HttpGet request = new HttpGet(url.toString());
        String result = null;
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() && entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
