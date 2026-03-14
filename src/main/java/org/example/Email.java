package org.example;

import java.util.Objects;

public final class Email {
    private final String address;

    public Email(String address) {
        if (address == null || !address.contains("@")) {
            throw new IllegalArgumentException("Формат вашої пошти некорректно введений");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return address.equals(email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "\nEmail: " + address;
    }
}
