package org.example;

import java.util.Objects;

public class OrderItem {
    private final String productId;
    private final String name;
    private final Money price;

    public OrderItem(String productId, String name, Money price) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("Введіть ID!");
        if (price == null) throw new IllegalArgumentException("Введіть ціну!");

        this.productId = productId;
        this.name = name;
        this.price = price;
    }
    public OrderItem(String productId, Money price) {
        this(productId, "Назва продукту", price);
    }

    public String getProductId(){
        return productId; }

    public String getName(){
        return name; }

    public Money getPrice(){
        return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return productId.equals(orderItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return  "\nID товару: " + productId +
                "\nНазва: " + name +
                "\nЦіна: " + price.getAmount() + " " + price.getCurrency();
    }
}