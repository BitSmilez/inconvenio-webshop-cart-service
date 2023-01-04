package com.bitsmilez.cartmicroservice.core.domain.service.interfaces;

import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.dto.ProductDTO;

public interface ICartService {


    CartDTO getProducts(String cartID);
    void addProduct(ProductDTO product);
}
