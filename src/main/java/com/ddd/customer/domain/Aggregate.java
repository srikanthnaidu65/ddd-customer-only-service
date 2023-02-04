package com.ddd.customer.domain;

import com.ddd.customer.domain.events.DomainEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public class Aggregate {

    List<DomainEvent> domainEvents = new ArrayList<>();

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void clearEvents() {
        domainEvents.clear();
    }
}
