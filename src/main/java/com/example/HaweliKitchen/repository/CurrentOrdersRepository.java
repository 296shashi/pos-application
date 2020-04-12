package com.example.HaweliKitchen.repository;

import com.example.HaweliKitchen.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentOrdersRepository extends JpaRepository<Orders,Integer> {

   // @Query("UPDATE Orders o SET o.pickup_time = :pickupTime WHERE o.order_id=:orderid")
   // public void  updatePickupTime(@Param("pickupTime") String pickupTime,@Param("orderid") int orderid);
   @Override
   Optional<Orders> findById(Integer orderId);
}
