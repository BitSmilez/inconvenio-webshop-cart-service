package com.bitsmilez.cartmicroservice.port.MQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductMessage {

    private UUID messageID;
    private String userID;
    private UUID productID;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productSalesPrice;
    private String productImg;
    private int quantity;

}