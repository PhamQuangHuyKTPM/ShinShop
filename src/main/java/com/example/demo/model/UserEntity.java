package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String fullname;

    private String password;
    private String email;

    private String phone;
    private String address;

    private Boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity cart;

    @JsonIgnore
    @OneToMany(mappedBy = "user_order")
    private List<OrderEntity> orders;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, String email, Boolean enable, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.userRoles = userRoles;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
