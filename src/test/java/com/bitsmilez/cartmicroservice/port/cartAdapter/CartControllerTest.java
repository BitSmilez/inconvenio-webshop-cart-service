package com.bitsmilez.cartmicroservice.port.cartAdapter;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.CartDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    private MockMvc mockMvc;
    String cartId = "1234";
    CartDTO cartDTO = new CartDTO(cartId);
    List<ProductDTO> products = generateProducts();

    @Mock
    private ICartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        cartDTO.setItems(products);
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }


    @Test
    public void getProducts_whenProductsFound() throws Exception {
        when(cartService.getProducts(cartId)).thenReturn(cartDTO);

        MvcResult result = mockMvc.perform(get("/cart/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        CartDTO receivedCart = new ObjectMapper().readValue(body, CartDTO.class);
        assertEquals("CartIDs should match", cartDTO.getCartID(), receivedCart.getCartID());
        assertEquals("Cart Size should match", cartDTO.getItems().size(), receivedCart.getItems().size());
        assertEquals("First cart Item should match", cartDTO.getItems().get(0).getProductID(), receivedCart.getItems().get(0).getProductID());
    }

    @Test
    public void getProducts_whenCartIdIsEmpty() throws Exception {
        String emptyCartId = "444";
        CartDTO emptyCart = new CartDTO(emptyCartId);
        when(cartService.getProducts(emptyCartId)).thenReturn(emptyCart);

        MvcResult result = mockMvc.perform(get("/cart/{cartId}", emptyCartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        CartDTO receivedCart = new ObjectMapper().readValue(body, CartDTO.class);
        assertEquals("CartIDs should match", emptyCart.getCartID(), receivedCart.getCartID());
        assertEquals("Received Cart should be empty", 0, receivedCart.getItems().size());
        assertEquals("Both Carts should be empty", emptyCart.getItems().size(), receivedCart.getItems().size());
    }

    @Test
    public void getProducts_whenProductsNotFound() throws Exception {
        when(cartService.getProducts(cartId)).thenReturn(null);

        mockMvc.perform(get("/cart/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getProducts_whenCartIdIsInvalid() throws Exception {
        mockMvc.perform(get("/cart/{cartId}", "invalid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    private List<ProductDTO> generateProducts() {

        ProductDTO product1 = Mapper.toProductDTO(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 1", BigDecimal.valueOf(10.00), null, null, 1));
        ProductDTO product2 = Mapper.toProductDTO(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 2", BigDecimal.valueOf(20.00), null, null, 1));
        ProductDTO product3 = Mapper.toProductDTO(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 3", BigDecimal.valueOf(30.00), null, null, 1));

        List<ProductDTO> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }


}