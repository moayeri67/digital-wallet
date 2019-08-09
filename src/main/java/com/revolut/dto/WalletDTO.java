package com.revolut.dto;

public class WalletDTO {
    private int fromAccountNumber;
    private int toAccountNumber;
    private int currency;
    private String amount;

    public WalletDTO() {
    }

    public WalletDTO(int fromAccountNumber, int currency, String amount) {
        this.fromAccountNumber = fromAccountNumber;
        this.currency = currency;
        this.amount = amount;
    }

    public WalletDTO(int fromAccountNumber, int toAccountNumber, int currency, String amount) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.currency = currency;
        this.amount = amount;
    }

    public int getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(int fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(int toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "fromAccountNumber=" + fromAccountNumber +
                ", toAccountNumber=" + toAccountNumber +
                ", currency=" + currency +
                ", amount='" + amount + '\'' +
                '}';
    }
}