package com.example.demo.repository;

import com.example.demo.model.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<RevenueEntity, Long> {

    RevenueEntity findByDate(LocalDate date);
    List<RevenueEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
