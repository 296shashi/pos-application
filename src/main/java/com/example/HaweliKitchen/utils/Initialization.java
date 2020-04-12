package com.example.HaweliKitchen.utils;

import com.example.HaweliKitchen.services.OrderProcessingWorkers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public  class Initialization {

    private static ExecutorService executor;

    public static ExecutorService getExecutor() {
        return executor;
    }

    public static void execute(){
        executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads
    }

}
