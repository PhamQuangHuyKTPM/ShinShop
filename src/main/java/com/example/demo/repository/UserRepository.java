package com.example.demo.repository;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import org.hibernate.id.IncrementGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_role (role_id, user_id) VALUES (:roleId, :userId );", nativeQuery = true)
    void saveUserRole(Integer roleId, Long userId);
    UserEntity findById(Integer id);
}
