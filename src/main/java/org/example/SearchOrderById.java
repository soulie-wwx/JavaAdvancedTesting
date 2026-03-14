package org.example;

import java.util.Optional;

public interface SearchOrderById {
    Optional<Order> findById(String orderId);

    void process(String orderId);
}
