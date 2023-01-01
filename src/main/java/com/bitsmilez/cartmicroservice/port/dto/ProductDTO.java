package com.bitsmilez.cartmicroservice.port.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private UUID productID;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productSalesPrice;
    private String productImg;
    private int quantity;
    @JsonIgnore
    private CartDTO cart;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productSalesPrice=" + productSalesPrice +
                ", productImg='" + productImg + '\'' +
                ", quantity=" + quantity +
                ", cart=" + cart.getCartID() +
                '}';
    }
}
