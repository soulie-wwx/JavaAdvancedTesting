package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public abstract class OrderProcessorTemplate implements SearchOrderById {
    protected static final Logger logger = Logger.getLogger(OrderProcessorTemplate.class.getName());
    protected final PaymentMethod paymentMethod;
    protected abstract double calculateTotal(Order order);
    protected abstract void completeAndNotify(Order order) throws NotificationException;


    public OrderProcessorTemplate(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public final void process(String orderId) {
        logger.info("Початок обробки замовлення: " + orderId);
        try {
            Order order = findById(orderId).orElseThrow(() -> new AppException("Замовлення не знайдено: " + orderId));
            validateOrder(order);
            validatePromoCode(order);
            double total = calculateTotal(order);
            performPayment(order, total);
            logger.info("Замовлення успішно оброблено: " + orderId);
        } catch (AppException e) {
            logger.warning("Бізнес-помилка під час обробки: " + e.getMessage());
            throw e;
        }
    }


    protected void validateOrder(Order order) {
        logger.info("Валідація замовлення...");
        OrderItem[] items = order.getItems();
        if (items.length == 0) {
            throw new OrderValidationException("Замовлення порожнє");
        }
        Set<String> uniqueProductIds = new HashSet<>();
        for (OrderItem item : items) {
            if (!uniqueProductIds.add(item.getProductId())) {
                throw new OrderValidationException("Знайдено дублікат товару: " + item.getProductId());
            }
        }
        order.setStatus(OrderStatus.VALIDATED);
    }

    protected void validatePromoCode(Order order) {
        String promo = order.getPromoCode();
        if (promo != null && !promo.isBlank()) {
            logger.info("Перевіряємо промокод: " + promo);
            if (!"PROMO".equals(promo)) {
                throw new InvalidPromoCodeException("Недійсний промокод: " + promo);
            }
        }
    }

    protected void performPayment(Order order, double amount) {
        logger.info("Оплачуємо на суму: " + amount);
        paymentMethod.pay(new Money(amount, "UAH"));
        order.setStatus(OrderStatus.PAID);
    }

}