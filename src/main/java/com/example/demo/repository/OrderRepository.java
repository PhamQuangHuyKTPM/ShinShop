package com.example.demo.repository;

import com.example.demo.model.OrderEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findById(Integer id);

    @Query(value = "SELECT * FROM orders where user_id = :id", nativeQuery = true)
    List<OrderEntity> findAllByUser_order(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE OrderEntity o SET o.status = :statusNew WHERE o.id = :id")
    void updateStatusById(Long id, String statusNew);
}
