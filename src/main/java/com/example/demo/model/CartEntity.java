package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping-cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalItems;
    private double totalPrices;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItemEntity> cartItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(double totalPrices) {
        this.totalPrices = totalPrices;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public String getTotalPriceVND() {
        // Định dạng số với dấu phân cách hàng nghìn và không có chữ số sau dấu thập phân
        DecimalFormat df = new DecimalFormat("#,##0");
        String formattedPrice = df.format(this.totalPrices);
        formattedPrice += " VNĐ";

        return formattedPrice;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", totalItems=" + totalItems +
                ", totalPrices=" + totalPrices +
                ", user=" + user +
                ", cartItems=" + cartItems +
                '}';
    }

}
