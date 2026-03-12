package com.logistics.mock.dto;

import com.logistics.mock.model.OrderStatus;

public class ChangeOrderStatusRequest {

    private String orderId;
    private OrderStatus status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
