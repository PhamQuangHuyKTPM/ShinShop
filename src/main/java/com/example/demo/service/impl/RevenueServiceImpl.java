package com.example.demo.service.impl;

import com.example.demo.model.RevenueEntity;
import com.example.demo.repository.RevenueRepository;
import com.example.demo.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Override
    public RevenueEntity findByDate(LocalDate date) {
        return revenueRepository.findByDate(date);
    }

    @Override
    public List<RevenueEntity> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return revenueRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<RevenueEntity> findByDateYear(int year) {
        return null;
    }

    @Override
    public void save(RevenueEntity revenue) {
        revenueRepository.save(revenue);
    }

    @Override
    public List<RevenueEntity> getAll() {
        return revenueRepository.findAll();
    }

    @Override
    public List<Object[]> getRevenueFromDateToDate(LocalDate startDate, LocalDate endDate) {
        return revenueRepository.getRevenueFromDateToDate(startDate, endDate);
    }
}
