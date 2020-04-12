package com.example.HaweliKitchen.controller;


import com.example.HaweliKitchen.exceptions.FoodMenuNotFoundExceptions;
import com.example.HaweliKitchen.model.FoodItems;
import com.example.HaweliKitchen.repository.FoodItemRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AddMenuController {

    @Autowired
    private FoodItemRepository fditem;

    @RequestMapping(value = "/getitems/{id}",method = RequestMethod.GET )
    @ResponseBody
    public FoodItems getItems(@PathVariable int id)
    {
        return fditem.findById(id)
                .orElseThrow(() -> new FoodMenuNotFoundExceptions(id));

    }

    @RequestMapping(value = "/additems/",method = RequestMethod.POST )
    @ResponseBody
    public String menuUpload(@RequestParam("file") MultipartFile file) {
        int itemid=0;

        if (file.isEmpty()) {
            return "empty file";
        }
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String line;
            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null && line !="") {
                System.out.println(line);
                String[] arrSplit = line.split(",");
                FoodItems item=new FoodItems(arrSplit[0],Integer.valueOf(arrSplit[1]),Float.valueOf(arrSplit[2]));
                try {
                    fditem.save(item);
                } catch (Exception e) {
                    return "Could not store file " + fileName + ". Please try again!";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Menu uploaded successfully";
    }

    /*

    @RequestMapping(value = "/additems/menu",method = RequestMethod.POST )
    @ResponseBody
    public String addItemByMenu(@Valid @ModelAttribute("employee") FoodItems item,BindingResult result, ModelMap model)
    {
        fditem.save(item);

        return "Succesfully added";

    }

     */

}
