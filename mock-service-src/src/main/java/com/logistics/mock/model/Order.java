package com.logistics.mock.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

    private Long orderId;
    private Long warehouseId;
    private String userEmail;
    private String userId;
    private Date createTime;
    private Date deliveryTime;
    private String address;
    private double distance;
    private int weight;
    private WarehouseType type;
    private BigDecimal price;
    private OrderStatus status;

    public Order() {
    }

    public Order(Long orderId, Long warehouseId, String userEmail, String userId,
                 Date createTime, Date deliveryTime, String address, double distance,
                 int weight, WarehouseType type, BigDecimal price, OrderStatus status) {
        this.orderId = orderId;
        this.warehouseId = warehouseId;
        this.userEmail = userEmail;
        this.userId = userId;
        this.createTime = createTime;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.distance = distance;
        this.weight = weight;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
