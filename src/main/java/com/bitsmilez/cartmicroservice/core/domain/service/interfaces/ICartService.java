package com.bitsmilez.cartmicroservice.core.domain.service.interfaces;

import com.bitsmilez.cartmicroservice.port.dto.CartDTO;

import java.math.BigDecimal;
import java.util.UUID;


public interface ICartService {


    CartDTO getProducts(String cartID);
    void addProduct(String cartID, UUID productID, String productName, BigDecimal productPrice, BigDecimal productSalesPrice, String productImg, int quantity);
}
