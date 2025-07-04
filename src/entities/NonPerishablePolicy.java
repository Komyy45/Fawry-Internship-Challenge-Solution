package entities;

import contracts.ExpirationPolicy;

public class NonPerishablePolicy implements ExpirationPolicy {
    @Override
    public boolean isExpired() {
        return false;
    }
}
