package com.example.HaweliKitchen.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public void readFromCSV()
    {
        String csvFile = "C://Users/Shashi/Desktop/callCDR.csv";
        String cvsSplitBy = ",";
        int cnt=1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                // System.out.println(country[0] + " "+ " " + country[1] + " " + country[2] + " " + country[3]  + "]");
            }
            System.out.println("CSV reading done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
