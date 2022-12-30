package com.bitsmilez.cartmicroservice.core.domain.service.interfaces;

import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public interface ICartService {


    CartDTO getProducts(String cartID);
    void addProduct(String cartID, UUID productID, String productName, BigDecimal productPrice, BigDecimal productSalesPrice, String productImg, int quantity);
}
