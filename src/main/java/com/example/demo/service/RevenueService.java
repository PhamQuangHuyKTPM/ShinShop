package com.example.demo.service;

import com.example.demo.model.RevenueEntity;

import java.time.LocalDate;
import java.util.List;

public interface RevenueService {

    RevenueEntity findByDate(LocalDate date);
    List<RevenueEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<RevenueEntity> findByDateYear(int year);

    void save(RevenueEntity revenue);

    List<RevenueEntity> getAll();

    List<Object[]> getRevenueFromDateToDate(LocalDate startDate,LocalDate endDate);

}
