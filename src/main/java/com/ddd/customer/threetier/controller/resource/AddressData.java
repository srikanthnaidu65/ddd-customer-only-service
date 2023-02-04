package com.ddd.customer.threetier.controller.resource;

import com.ddd.customer.domain.Address;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public class AddressData {
    private String city;

    public AddressData() {
    }

    public static AddressData from(Address address) {
        AddressData addressData = new AddressData();
        addressData.setCity(address.getCity());
        return addressData;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
