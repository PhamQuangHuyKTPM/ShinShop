package com.example.demo.controller.admin;

import com.example.demo.model.RevenueEntity;
import com.example.demo.service.RevenueService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("")
    public String dashBoard(){

        return "admin/dashboard/index";
    }


}
