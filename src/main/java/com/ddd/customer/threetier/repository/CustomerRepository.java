package com.ddd.customer.threetier.repository;

import com.ddd.customer.domain.CustomerId;
import com.ddd.customer.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author srikanth
 * @since 04/02/2023
 * <p>
 * Customer Repository or Data Access Layer
 * Using in-memory map instead of db for demo
 */
@Repository
public class CustomerRepository {

    private Map<CustomerId, Customer> customerMap = new HashMap<>();

    public Customer save(Customer customer) {
        System.out.println("in Repository customer = " + customer);
        customerMap.put(customer.getCustomerId(), customer);
        return customer;
    }

    public Customer find(CustomerId customerId) {
        return customerMap.get(customerId);
    }
}
