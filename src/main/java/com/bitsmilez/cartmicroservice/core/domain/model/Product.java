package com.bitsmilez.cartmicroservice.core.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private UUID productID;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productSalesPrice;
    private String productImg;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;



}
