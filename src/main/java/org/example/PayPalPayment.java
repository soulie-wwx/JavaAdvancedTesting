package org.example;

import java.util.logging.Logger;

public class PayPalPayment implements PaymentMethod {
    private static final Logger logger = Logger.getLogger(PayPalPayment.class.getName());
    private static final double MIN_AMOUNT = 500.0;

    @Override
    public void pay(Money amount) {
        if (amount.getAmount() < MIN_AMOUNT) {
            logger.warning("Оплата відхилена: ваша сума " + amount.getAmount() + " менша за 500.");
            throw new IllegalArgumentException("PayPalPayment: не може бути менше 500");
        }

        logger.info("Успішно оплачено " + amount + " через PayPal.");
    }
}