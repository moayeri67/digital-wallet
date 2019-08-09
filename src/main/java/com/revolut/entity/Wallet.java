package com.revolut.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Wallet implements Serializable {
    private Integer id;
    private Integer currencyId;
    private Integer accountNumber;
    private BigDecimal balance;

    public Wallet() {
    }

    public Wallet(int userId, int currencyId) {
        this.accountNumber = userId;
        this.currencyId = currencyId;
    }

    public Wallet(int id, int userId, int currencyId, BigDecimal balance) {
        this.id = id;
        this.accountNumber = userId;
        this.currencyId = currencyId;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getUserId() {
        return accountNumber;
    }

    public void setUserId(Integer userId) {
        this.accountNumber = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", currencyId=" + currencyId +
                ", userId=" + accountNumber +
                ", balance=" + balance +
                '}';
    }
}