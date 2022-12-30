package com.bitsmilez.cartmicroservice.core.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String cartID;
    @OneToMany(mappedBy="cart")
    private Set<Product> items;

    public Cart(String cartID) {
        this.cartID=cartID;
    }
}
