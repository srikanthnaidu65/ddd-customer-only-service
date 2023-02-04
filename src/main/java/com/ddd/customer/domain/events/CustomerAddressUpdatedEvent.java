package com.ddd.customer.domain.events;

import java.util.UUID;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public class CustomerAddressUpdatedEvent implements DomainEvent {
    private UUID customerId;
    private AddressData address;

    public CustomerAddressUpdatedEvent(UUID customerId, AddressData address) {
        this.customerId = customerId;
        this.address = address;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public AddressData getAddress() {
        return address;
    }
}
