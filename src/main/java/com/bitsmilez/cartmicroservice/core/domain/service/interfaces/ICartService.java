package com.bitsmilez.cartmicroservice.core.domain.service.interfaces;

import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.CartDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;

public interface ICartService {


    CartDTO getProducts(String cartID);

    void addProduct(ProductDTO product);

    void removeProduct(String cartID, String productID);
}
