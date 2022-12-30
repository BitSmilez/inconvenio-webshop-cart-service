package com.bitsmilez.cartmicroservice.core.domain.service.impl;

import com.bitsmilez.cartmicroservice.core.domain.model.Cart;
import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartRepository;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    ICartRepository cartRepository;
    @Autowired
    IProductRepository productRepository;
    public CartServiceImpl(ICartRepository cartRepository, IProductRepository productRepository) {
        super();
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }




    @Override
    public CartDTO getProducts(String cartID) {
        Cart cart = new Cart();
        cart.setCartID(cartID);
        cart.setItems(new HashSet<>());
        Product product = new Product();
        product.setProductID(new UUID(1,1));
        product.setProductName("test");
        product.setProductPrice(new BigDecimal(1));
        product.setProductSalesPrice(null);
        product.setProductImg("test");
        product.setQuantity(5);
        product.setCart(cart);

        cartRepository.save(cart);
        productRepository.save(product);

        Optional<Cart> savedCart = cartRepository.findById(cartID);
        savedCart.get().getItems().add(product);
        System.out.println(savedCart.get().getItems());

        return savedCart.map(Mapper::toCartDTO).orElse(null);






    }

    @Override
    public void addProduct(String cartID, UUID productID, String productName, BigDecimal productPrice, BigDecimal productSalesPrice, String productImg, int quantity) {

    }


}
