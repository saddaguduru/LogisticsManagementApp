package com.logistics.mock.model;

import java.math.BigDecimal;

public class FullUserInfo {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private BigDecimal balance;

    public FullUserInfo() {
    }

    public FullUserInfo(Long id, String username, String email, Role role, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.balance = balance;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
