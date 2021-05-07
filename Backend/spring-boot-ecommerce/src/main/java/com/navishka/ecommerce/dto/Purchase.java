package com.navishka.ecommerce.dto;

import com.navishka.ecommerce.entity.Address;
import com.navishka.ecommerce.entity.Customer;
import com.navishka.ecommerce.entity.Order;
import com.navishka.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
