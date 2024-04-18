package com.example.demo.repository;

import com.example.demo.model.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<RevenueEntity, Long> {

    RevenueEntity findByDate(LocalDate date);
    List<RevenueEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT SUM(revenue) AS total_revenue, SUM(total_order) AS total_order, :startDate AS start_date, :endDate AS end_date FROM revenue WHERE date >= :startDate AND date <= :endDate", nativeQuery = true)
    List<Object[]> getRevenueFromDateToDate(LocalDate startDate,LocalDate endDate);

}
