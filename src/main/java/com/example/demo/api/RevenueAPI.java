package com.example.demo.api;

import com.example.demo.model.RevenueEntity;
import com.example.demo.service.RevenueService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/revenue")
public class RevenueAPI {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/linechart")
    public ResponseEntity<?> getDataLineChart(){
        List<RevenueEntity> revenue = revenueService.getAll();
        JsonArray jsonDate = new JsonArray();
        JsonArray jsonRevenue = new JsonArray();
        JsonObject json = new JsonObject();
        revenue.forEach(data->{
            jsonDate.add(data.getFormattedOrderDate());
            jsonRevenue.add(data.getRevenue());
        });
        json.add("date", jsonDate);
        json.add("revenue", jsonRevenue);
        return ResponseEntity.ok(json.toString());
    }

    @PostMapping("")
    public ResponseEntity<?> getDataMonth(){
        int year = 2024; // Năm
        int month = 4; // Tháng (1-12)

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());

        List<List<Object[]>> listOfRevenueWeek = new ArrayList<>();

        LocalDate currentDay = firstDayOfMonth;
        while (currentDay.isBefore(lastDayOfMonth) || currentDay.isEqual(lastDayOfMonth)) {
            LocalDate startOfWeek = currentDay.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate endOfWeek = currentDay.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
            System.out.println("Week: " + startOfWeek + " - " + endOfWeek);

            List<Object[]> week = revenueService.getRevenueFromDateToDate(startOfWeek, endOfWeek);
            listOfRevenueWeek.add(week);
            currentDay = endOfWeek.plusDays(1);
        }

        for (List<Object[]> results : listOfRevenueWeek) {
            // Lặp qua từng mảng Object[] trong danh sách kết quả
            for (Object[] result : results) {
                System.out.println(result[0] + " - " + result[1] + " - " + result[2] + " - " + result[3]) ;
            }
        }

        return ResponseEntity.ok(listOfRevenueWeek);
    }
}
