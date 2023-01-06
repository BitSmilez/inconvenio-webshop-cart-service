package com.bitsmilez.cartmicroservice.port.cartAdapter;

import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    ICartService cartService;

    public CartController(ICartService cartService) {
        super();
        this.cartService = cartService;
    }

    @GetMapping(value = "/cart/{cartID}")
    public CartDTO getProducts(@PathVariable(name = "cartID") String cartID) {
        return cartService.getProducts(cartID);
    }
}
