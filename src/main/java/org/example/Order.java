package org.example;

import java.util.Arrays;

public class Order {
    private final String orderId;
    private final Email customerEmail;

    private final OrderItem[] items;

    private OrderStatus status;
    private String promoCode;

    public Order(String orderId, Email customerEmail, OrderItem[] items) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;

        if (items == null) {
            this.items = new OrderItem[0];
        } else {
            this.items = Arrays.copyOf(items, items.length);
        }
        this.status = OrderStatus.NEW;
    }
    public Order(String orderId, Email customerEmail, OrderItem[] items, String promoCode) {
        this(orderId, customerEmail, items);
        this.promoCode = promoCode;
    }

    public int getItemsCount() {
        return this.items.length;
    }

    public OrderItem[] getItems() {
        return Arrays.copyOf(this.items, this.items.length);
    }

    public void setStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.PAID && newStatus != OrderStatus.REFUNDED && newStatus != OrderStatus.COMPLETED) {
            throw new IllegalStateException("Зі стану PAID можна перейти лише в REFUNDED або COMPLETED");
        }
        this.status = newStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public Email getCustomerEmail() {
        return customerEmail;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getPromoCode() {
        return promoCode;
    }
}