package com.logistics.mock.dto;

import com.logistics.mock.model.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class FiatTransactionDTO {

    private String userId;
    private String invoiceId;
    private BigDecimal amount;
    private String currency;
    private Date createdAt;
    private PaymentStatus status;

    public FiatTransactionDTO() {
    }

    public FiatTransactionDTO(String userId, String invoiceId, BigDecimal amount,
                               String currency, Date createdAt, PaymentStatus status) {
        this.userId = userId;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
