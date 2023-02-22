package com.bitsmilez.cartmicroservice.core.domain.service.impl;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.CartDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    String cartId;
    ArrayList<Product> products;

    @BeforeEach
    void setUp() {
        cartId = "123";
        products = generateProducts();
    }

    @Test
    public void getProducts_whenProductsFound() {

        when(productRepository.findByProductIDCartID(cartId)).thenReturn(products);

        CartDTO result = cartService.getProducts(cartId);

        assertNotNull(result);
        assertEquals("CartID should be 123", cartId, result.getCartID());
        assertEquals("Cart Size should be 3", products.size(), result.getItems().size());
    }

    @Test
    public void getProducts_whenProductsNotFound() {

        when(productRepository.findByProductIDCartID(cartId)).thenReturn(new ArrayList<>());

        CartDTO result = cartService.getProducts(cartId);

        assertNull(result);
    }

    @Test
    public void addProduct_whenProductExists() {

        Product product = products.get(0);
        ProductDTO productDTO = Mapper.toProductDTO(product);

        when(productRepository.findById(product.getProductID())).thenReturn(Optional.of(product));

        cartService.addProduct(productDTO);

        assertEquals("Quantity should be 2", 2, product.getQuantity());
    }

    @Test
    public void addProduct_whenProductDoesNotExist() {

        Product product = products.get(0);
        ProductDTO productDTO = Mapper.toProductDTO(product);

        when(productRepository.findById(product.getProductID())).thenReturn(Optional.empty());

        cartService.addProduct(productDTO);

        assertEquals("Quantity should be 1", 1, product.getQuantity());
    }

    @Test
    public void removeProducts_all() {

        when(productRepository.findByProductIDCartID(cartId)).thenReturn(products);
        CartDTO resultWithItems = cartService.getProducts(cartId);
        assertNotNull(resultWithItems);
        when(productRepository.findByProductIDCartID(cartId)).thenReturn(new ArrayList<>());
        cartService.removeAllProducts(cartId);
        CartDTO resultEmpty = cartService.getProducts(cartId);

        assertEquals("Cart Size before should be 3", products.size(), resultWithItems.getItems().size());
        assertNotEquals(resultWithItems, resultEmpty);
        assertNull(resultEmpty);
    }

    @Test
    public void deleteProduct_byProductID() {

        Product product = products.get(0);
        ProductDTO productDTO = Mapper.toProductDTO(product);

        cartService.addProduct(productDTO);

        when(productRepository.findById(product.getProductID())).thenReturn(Optional.of(product));
        cartService.removeProduct(cartId, product.getProductID().getProductID().toString());

        CartDTO result = cartService.getProducts(cartId);

        assertNull(result);

    }

    @Test
    public void updateProduct() {

        Product product = products.get(0);
        ProductDTO productDTO = Mapper.toProductDTO(product);

        when(productRepository.findById(product.getProductID())).thenReturn(Optional.of(product));
        cartService.addProduct(productDTO);
        assertEquals("Quantity should be 2", 2, product.getQuantity());

        cartService.updateProduct(cartId, productDTO.getProductID().toString(), 3);
        assertEquals("Quantity should be 3", 3, product.getQuantity());
    }


    private ArrayList<Product> generateProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 1", BigDecimal.valueOf(10.00), null, null, 1));
        products.add(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 2", BigDecimal.valueOf(20.00), null, null, 2));
        products.add(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 3", BigDecimal.valueOf(30.00), null, null, 3));
        return products;
    }

}