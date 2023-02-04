package com.ddd.customer.domain.events;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public class AddressData {

    private String city;

    public AddressData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
