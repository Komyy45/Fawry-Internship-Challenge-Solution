package entities;

import contracts.ExpirationPolicy;

import java.time.LocalDate;

public class PerishablePolicy implements ExpirationPolicy {
    private final LocalDate date;

    public PerishablePolicy(LocalDate date)
    {
        this.date = date;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(date) || LocalDate.now().isEqual(date);
    }
}
