package com.ddd.customer.domain;

import java.util.UUID;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public class AccountId {

    private UUID accountId;

    public AccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getAccountIdUUID() {
        return accountId;
    }

    @Override
    public String toString() {
        return "AccountId{" +
                "accountId=" + accountId +
                '}';
    }
}
