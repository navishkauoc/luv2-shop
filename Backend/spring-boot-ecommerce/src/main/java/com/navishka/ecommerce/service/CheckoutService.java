package com.navishka.ecommerce.service;

import com.navishka.ecommerce.dto.Purchase;
import com.navishka.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
