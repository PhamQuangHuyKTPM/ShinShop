package com.example.demo;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class testPassword {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

        int year = 2024; // Năm
        int month = 4; // Tháng (1-12)

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());

        LocalDate currentDay = firstDayOfMonth;
        while (currentDay.isBefore(lastDayOfMonth) || currentDay.isEqual(lastDayOfMonth)) {
            LocalDate startOfWeek = currentDay.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate endOfWeek = currentDay.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
            System.out.println("Week: " + startOfWeek + " - " + endOfWeek);
            currentDay = endOfWeek.plusDays(1);
        }
    }
}
