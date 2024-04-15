package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.DecimalFormat;

@Entity
@Table(name = "order-details")
public class OrderDetailsEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
	@JoinColumn(name="product_id")
    @JsonIgnore
    private ProductEntity product;

    private double dongia;

    private double totalPrice;
    private Integer soluong;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

    public OrderDetailsEntity() {
    }

    public OrderDetailsEntity(Long id, ProductEntity product, Long dongia, Integer soluong, OrderEntity orderItem) {
        this.id = id;
        this.product = product;
        this.dongia = dongia;
        this.soluong = soluong;
        this.order = orderItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity orderItem) {
        this.order = orderItem;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPriceVND() {
        // Định dạng số với dấu phân cách hàng nghìn và không có chữ số sau dấu thập phân
        DecimalFormat df = new DecimalFormat("#,##0");
        String formattedPrice = df.format(this.totalPrice);
        formattedPrice += " VNĐ";

        return formattedPrice;
    }

    public String getDonGiaVND() {
        // Định dạng số với dấu phân cách hàng nghìn và không có chữ số sau dấu thập phân
        DecimalFormat df = new DecimalFormat("#,##0");
        String formattedPrice = df.format(this.dongia);
        formattedPrice += " VNĐ";
        return formattedPrice;
    }


}
