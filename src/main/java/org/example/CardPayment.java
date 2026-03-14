package org.example;

import java.util.logging.Logger;

public class CardPayment implements PaymentMethod {
    private static final Logger logger = Logger.getLogger(CardPayment.class.getName());
    private static final double MAX_AMOUNT = 40000.0;

    @Override
    public void pay(Money amount) {
        if (amount.getAmount() > MAX_AMOUNT) {
            logger.warning("Оплата відхилена: ваша сума " + amount.getAmount() + " перевищує ліміт 40 000.");
            throw new PaymentException("PaymentException: сума перевищує ліміт 40 000");
        }

        logger.info("Успішно оплачено " + amount + " через CardPayment.");
    }
}