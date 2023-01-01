package com.bitsmilez.cartmicroservice.port.dto;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data

public class CartDTO {
    private String cartID;
    private List<ProductDTO> items = new ArrayList<>();

    private BigDecimal total;

    public CartDTO(String cartID) {
        this.cartID=cartID;

    }
    public void addItem(ProductDTO product){
        items.add(product);
        product.setCart(this);
        calculateTotal();
    }

    public CartDTO(String cartID, List<ProductDTO> items) {
        this.cartID = cartID;
        this.items = items;
         calculateTotal();
    }

    public CartDTO() {
    }

    public void calculateTotal (){
        BigDecimal total = new BigDecimal("0.0");
        for (ProductDTO product: items) {
            BigDecimal price = product.getProductSalesPrice()!= null ? product.getProductSalesPrice() : product.getProductPrice();
            total =  total.add(price.multiply(BigDecimal.valueOf(product.getQuantity())));
        }
        this.total = total;
    }


    @Override
    public String toString() {
        return "CartDTO{" +
                "cartID='" + cartID + '\'' +
                ", items=" +items.size() +
                '}';
    }
}
