package com.bitsmilez.cartmicroservice.port.productAdapter;

import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    ICartService cartService;

    public CartController(ICartService cartService) {
        super();
        this.cartService = cartService;
    }
    @GetMapping("/test")
    public String getProducts() {
        CartDTO rep = cartService.getProducts("12345");
        System.out.println(rep);
        return "rep";
    }
}
