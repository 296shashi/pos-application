package com.example.HaweliKitchen;

import com.example.HaweliKitchen.utils.DashBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static com.example.HaweliKitchen.utils.Initialization.execute;

@SpringBootApplication
@EnableAutoConfiguration
public class HaweliKitchenApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaweliKitchenApplication.class, args);
        execute();

    }

}
