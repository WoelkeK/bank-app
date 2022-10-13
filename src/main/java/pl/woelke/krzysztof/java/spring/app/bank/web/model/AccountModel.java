package pl.woelke.krzysztof.java.spring.app.bank.web.model;

import org.springframework.format.annotation.NumberFormat;

public class AccountModel {
    private Long id;
    private String number;
    private double balance;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double currencyBalance;
    private String currency;
    private ClientModel client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCurrencyBalance() {
        return currencyBalance;
    }

    public void setCurrencyBalance(double currencyBalance) {
        this.currencyBalance = currencyBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }


    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", currencyBalance=" + currencyBalance +
                ", currency='" + currency + '\'' +
                ", client=" + client +
                '}';
    }
}
