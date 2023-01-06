package com.bitsmilez.cartmicroservice.port.cartAdapter;

import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.CartDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CartDTO> getProducts(@PathVariable(name = "cartID") String cartID) {
        CartDTO cart = cartService.getProducts(cartID);
        if(cart != null){
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

        }
    }
}
