package org.example;

import java.util.Optional;

public class OrderProcessorRealise extends OrderProcessorTemplate {

    private final Order currentOrder;
    public OrderProcessorRealise(PaymentMethod paymentMethod, Order currentOrder) {
        super(paymentMethod);
        this.currentOrder = currentOrder;
    }

    @Override
    public Optional<Order> findById(String orderId) {
        if (currentOrder != null && currentOrder.getOrderId().equals(orderId)) {
            return Optional.of(currentOrder);
        }
        return Optional.empty();
    }

    @Override
    protected double calculateTotal(Order order) {
        double sum = 0;
        for (OrderItem item : order.getItems()) {
            sum += item.getPrice().getAmount();
        }

        if ("SPRING15".equals(order.getPromoCode())) {
            sum *= 0.85;
            logger.info("Застосовано знижку 15%");
        }
        return sum;
    }

    @Override
    protected void completeAndNotify(Order order) throws NotificationException {
        order.setStatus(OrderStatus.COMPLETED);
        if (order.getCustomerEmail().getAddress().contains("Помилка")) {
            throw new NotificationException(
                    "Помилка відправки email",
                    new RuntimeException("Сервер недоступний"));
        }
        logger.info("Повідомлення надіслано успішно: " + order.getCustomerEmail().getAddress());
    }
}
