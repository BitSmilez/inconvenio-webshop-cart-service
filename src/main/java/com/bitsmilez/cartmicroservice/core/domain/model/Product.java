package com.bitsmilez.cartmicroservice.core.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @EmbeddedId
    private ProductID productID;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productSalesPrice;
    private String productImg;
    private int quantity;



    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productSalesPrice=" + productSalesPrice +
                ", productImg='" + productImg + '\'' +
                ", quantity=" + quantity +
                '}';
    }


}
