package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest{

    private Order order;

    @BeforeEach
    void setUp(){
        order = new Order(1, "Anna", 100.0);
    }

    @Test
    void newOrderHasStatusNew(){
    
    assertEquals("NEW", order.getStatus());
    }

    @Test
    void payTwiceThrowsException() {
    order.pay();

    assertThrows(IllegalStateException.class, () -> order.pay());
    }

    @Test
    void statusChangedToPaid(){
        order.pay();

        assertEquals("PAID", order.getStatus());
    }

    @Test
    void statusChangedToCancelled(){
        order.cancel();

        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void cancelTwiceThrowException(){
        order.cancel();

        assertThrows(IllegalStateException.class, () -> order.cancel());
    }

    @Test
    void orderCreatedWithCorrectCustomerNameAndAmount(){

        assertEquals("Anna", order.getCustomerName());
        assertEquals(100, order.getAmount(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 15, 99.99, 11.06})
    void orderStoresAnyAmount(double amount){
        Order order = new Order(1, "Lizi", amount);

        assertEquals(amount, order.getAmount());

    }
 }

