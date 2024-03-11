package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

    public RoleEntity() {
    }

    public RoleEntity(Long id, String roleName, Set<UserRole> userRoles) {
        this.id = id;
        this.roleName = roleName;
        this.userRoles = userRoles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
