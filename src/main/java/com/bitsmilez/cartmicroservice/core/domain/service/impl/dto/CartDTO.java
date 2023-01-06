package com.bitsmilez.cartmicroservice.core.domain.service.impl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDTO {
    private String cartID;
    private List<ProductDTO> items = new ArrayList<>();

    private BigDecimal subTotal;

    private BigDecimal vat;

    private BigDecimal netTotal;

    private double taxRate = 19.0;

    private int quantity;

    private BigDecimal shipping;
    public CartDTO(String cartID) {
        this.cartID=cartID;

    }

    public void setItems(List<ProductDTO> items) {
        this.items = items;
        calculateTotal();
        countTotalQuantity();
        calculateVat();

    }

    public void countTotalQuantity(){
        int totalQuantity=0;
        for (ProductDTO product: items) {
            totalQuantity+=product.getQuantity();
        }
        this.quantity=totalQuantity;
    }





    public void calculateTotal (){
        BigDecimal total = new BigDecimal("0.0");
        for (ProductDTO product: items) {
            BigDecimal price = product.getProductSalesPrice()!= null ? product.getProductSalesPrice() : product.getProductPrice();
            total =  total.add(price.multiply(BigDecimal.valueOf(product.getQuantity())));
        }
        this.subTotal = total;
    }

    public void calculateVat(){
        BigDecimal vatRate =  BigDecimal.valueOf(taxRate).divide(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(1));
        MathContext m = new MathContext(5);
        this.netTotal = (this.subTotal.divide(vatRate,m));
        this.vat = this.subTotal.subtract(this.netTotal);
    }
    @Override
    public String toString() {
        return "CartDTO{" +
                "cartID='" + cartID + '\'' +
                ", items=" +items.size() +
                '}';
    }
}
