package com.bitsmilez.cartmicroservice.port.dto;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private String cartID;
    private Set<Product> items;

    public CartDTO(String cartID) {
        this.cartID=cartID;
    }
}
