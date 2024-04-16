package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String payMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetailsEntity> orderDetails;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private LocalDate ngayDat;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private LocalDate ngayGiao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user_order;

    private String ghiChu;
    private double totalPrices;

    private double subtotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }
    public void setNgayGiao(LocalDate ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getFormattedOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ngayDat.format(formatter);
    }


    public LocalDate getNgayGiao() {
        return ngayGiao;
    }

    public void setOrderDateFromString(String ngayDat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.ngayDat = LocalDate.parse(ngayDat, formatter);
    }


    public UserEntity getUser_order() {
        return user_order;
    }

    public void setUser_order(UserEntity user_order) {
        this.user_order = user_order;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getTotalPrices() {
        return totalPrices;
    }

    public String getTotalPriceVND() {
        // Định dạng số với dấu phân cách hàng nghìn và không có chữ số sau dấu thập phân
        DecimalFormat df = new DecimalFormat("#,##0");
        String formattedPrice = df.format(this.totalPrices);
        formattedPrice += " VNĐ";

        return formattedPrice;
    }

    public void setTotalPrices(double totalPrices) {
        this.totalPrices = totalPrices;
    }

    public List<OrderDetailsEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getSubTotalVND() {
        // Định dạng số với dấu phân cách hàng nghìn và không có chữ số sau dấu thập phân
        DecimalFormat df = new DecimalFormat("#,##0");
        String formattedPrice = df.format(this.subtotal);
        formattedPrice += " VNĐ";
        return formattedPrice;
    }
}
