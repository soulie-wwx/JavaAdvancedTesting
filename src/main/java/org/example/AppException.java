package org.example;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}


class InvalidPromoCodeException extends AppException {
    public InvalidPromoCodeException(String message) {
        super(message);
    }
}

class OrderValidationException extends AppException {
    public OrderValidationException(String message) {
        super(message);
    }
}

class PaymentException extends AppException {
    public PaymentException(String message) {
        super(message);
    }
}

class NotificationException extends Exception {
    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}