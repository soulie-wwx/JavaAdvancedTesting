package org.example;

import java.util.logging.Logger;

public class BankFee implements PaymentMethod {
    private static final Logger logger = Logger.getLogger(BankFee.class.getName());
    private static final double COMMISSION_RATE = 0.03;

    @Override
    public void pay(Money amount) {
        double baseAmount = amount.getAmount();
        double commission = baseAmount * COMMISSION_RATE;
        double totalAmount = baseAmount + commission;

        logger.info("\nПроводиться переказ коштів." +
                "\nБазова сума: " + baseAmount + amount.getCurrency() +
                "\nКомісія (3%): " + commission + amount.getCurrency() +
                "\nДо сплати: " + totalAmount + amount.getCurrency());
    }
}