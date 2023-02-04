package com.ddd.customer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public class Customer extends Aggregate {
    private CustomerId customerId;
    private Address address;
    private List<AccountId> accountIds = new ArrayList<>();

    public Customer(Address address) {
        this.customerId = new CustomerId(UUID.randomUUID());
        this.address = address;
    }

    public void updateAddress(Address address) {
        this.address = address;
//        domainEvents.add(new CustomerAddressUpdatedEvent(customerId.getCustomerId(),
//                new AddressData(address.getCity())));
    }

    public void add(AccountId accountId) {
        accountIds.add(accountId);
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public String getCustomerIdAsString() {
        return customerId.getCustomerId().toString();
    }

    public Address getAddress() {
        return address;
    }

    public List<AccountId> getAccountIds() {
        return accountIds;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", address=" + address +
                ", accountIds=" + accountIds +
                '}';
    }
}
