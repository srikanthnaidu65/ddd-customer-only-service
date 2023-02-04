package com.ddd.customer.threetier.applicationservice;

import com.ddd.customer.domain.AccountId;
import com.ddd.customer.domain.Address;
import com.ddd.customer.domain.CustomerId;
import com.ddd.customer.threetier.controller.resource.AddressData;
import com.ddd.customer.threetier.repository.CustomerRepository;
import com.ddd.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * @author srikanth
 * @since 04/02/2023
 */
@Service
public class CustomerAppService {

    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public CustomerAppService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //@Transactional
    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    // Choreography Saga
    // Orchestrator Saga
    // Kafka partition aggregate id

    //@Transactional
    public Customer updateAddress(UUID customerId, Address address) {
        Customer customer = customerRepository.find(new CustomerId(customerId));

        Address oldAddress = customer.getAddress();

        customer.updateAddress(address);

        customerRepository.save(customer);

        List<String> accountIds = customer.getAccountIds().stream()
                .map(accountId -> accountId.getAccountIdUUID().toString()).toList();

        AddressRequest request = new AddressRequest();
        request.setCity(address.getCity());
        AddressRequest request1 = new AddressRequest();
        request.setCity(address.getCity());
        accountIds.forEach(accountId ->
                restTemplate.put("http://localhost:8082/accounts/" + accountId + "/address",
                        request1, AccountResource.class));

        return customer;

//        List<DomainEvent> domainEvents = customer.getDomainEvents();
//        domainEvents.forEach( domainEvent -> {
//            kafkaTemplate.send("customer-topic", domainEvent);
//        });
//        customer.clearEvents();
//        commit Customer to database
//        Transaction outbox pattern to atomically put
//        events on kafka and write customer to relational DB.

//        Customer savedCustomer = customerRepository.save(customer);
//        commit transaction
//
    }

    public Customer addAccount(UUID customerId, String accountId) {
        Customer customer = customerRepository.find(new CustomerId(customerId));
        customer.add(new AccountId(UUID.fromString(accountId)));
        customerRepository.save(customer);
        return customer;
    }

    class AddressRequest {
        private String city;

        //needed for spring framework
        public AddressRequest() {
        }

        //needed for spring framework
        public String getCity() {
            return city;
        }

        //needed for spring framework
        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "AddressRequest{" +
                    "city='" + city + '\'' +
                    '}';
        }
    }

    class AccountResource {
        private UUID accountId;
        private AddressData addressData;

        public AccountResource() {
        }

        public UUID getAccountId() {
            return accountId;
        }

        public void setAccountId(UUID accountId) {
            this.accountId = accountId;
        }

        public AddressData getAddressData() {
            return addressData;
        }

        public void setAddressData(AddressData addressData) {
            this.addressData = addressData;
        }
    }
}
