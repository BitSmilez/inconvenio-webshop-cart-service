package com.bitsmilez.cartmicroservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductMessage {

    private String messageID;
    private String productID;
    private String userID;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productSalesPrice;
    private String productImg;
    private int quantity;

}
