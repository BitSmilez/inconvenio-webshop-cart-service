package com.bitsmilez.cartmicroservice.core.domain.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    private String cartID;

    @OneToMany(cascade = CascadeType.ALL,

            mappedBy = "cart")
    private List<Product> items = new ArrayList<>();

    public Cart(String cartID) {
        this.cartID = cartID;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID='" + cartID + '\'' +
                ", items=" +items.size() +
                '}';
    }
}
