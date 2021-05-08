package com.navishka.ecommerce.service;

import com.navishka.ecommerce.dao.CustomerRepository;
import com.navishka.ecommerce.dto.Purchase;
import com.navishka.ecommerce.dto.PurchaseResponse;
import com.navishka.ecommerce.entity.Customer;
import com.navishka.ecommerce.entity.Order;
import com.navishka.ecommerce.entity.OrderItem;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Retrieve the order info from dto
        Order order = purchase.getOrder();

        // Generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();

        // Populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(orderItem -> order.add(orderItem));

        // Populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // Populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // Save to the database
        customerRepository.save(customer);

        // Return response
        return new PurchaseResponse(orderTrackingNumber);
        
    }

    private String generateOrderTrackingNumber() {
        // Generate a random UUID number (UUID version-4)
        // Doc: https://en.wikipedia.org/wiki/Universally_unique_identifier
        return UUID.randomUUID().toString();
    }
}
