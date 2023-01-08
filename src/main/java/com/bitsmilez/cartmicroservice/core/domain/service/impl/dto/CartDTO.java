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

    private BigDecimal subTotalVat;


    private double taxRate = 19.0;

    private int quantity;

    private BigDecimal shppingFreeLimit= new BigDecimal("100.00");
    private BigDecimal shipping = new BigDecimal("5.00");

    private BigDecimal total;
    private BigDecimal totalVat;
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
        BigDecimal subTotal = new BigDecimal("0.0");
        for (ProductDTO product: items) {
            BigDecimal price = product.getProductSalesPrice()!= null ? product.getProductSalesPrice() : product.getProductPrice();
            subTotal =  subTotal.add(price.multiply(BigDecimal.valueOf(product.getQuantity())));
        }
        this.subTotal = subTotal;
        this.shipping = subTotal.compareTo(shppingFreeLimit) >= 0 ? new BigDecimal("0.0") : shipping;

        this.total = subTotal.add(this.shipping);
    }

    public void calculateVat(){
        MathContext mc = new MathContext(3);
        BigDecimal taxRate = (new BigDecimal(this.taxRate)).divide(new BigDecimal("100.0"),mc);
        BigDecimal divisor = BigDecimal.valueOf(1).add((taxRate));

        System.out.println("divisor: "+divisor);
        System.out.println("taxRate: "+taxRate);
        System.out.println("subTotal: "+subTotal);
        this.subTotalVat = this.subTotal.divide(divisor,MathContext.DECIMAL64).multiply(taxRate,mc);
        this.totalVat = this.total.divide(divisor,MathContext.DECIMAL64).multiply(taxRate,mc);


    }
    @Override
    public String toString() {
        return "CartDTO{" +
                "cartID='" + cartID + '\'' +
                ", items=" +items.size() +
                '}';
    }
}
