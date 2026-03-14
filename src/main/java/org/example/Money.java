package org.example;

import java.util.Objects;

public final class Money {
    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сума не може бути від'ємною");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Валюта не може бути порожньою");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public static Money uah(double amount) {
        return new Money(amount, "UAH");
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return money.amount == amount && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}