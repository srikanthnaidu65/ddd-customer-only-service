package com.ddd.customer.domain;

/**
 * @author srikanth
 * @since 04/02/2023
 */
public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
