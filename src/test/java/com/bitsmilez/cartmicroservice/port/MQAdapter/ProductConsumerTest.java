package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.ProductMessage;
import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductConsumerTest {

    @Mock
    private ICartService cartService;

    @InjectMocks
    private ProductConsumer productConsumer;
    String cartId = "1234";

    @Test
    public void testReceiveProductMessage() {
        ProductDTO product = Mapper.toProductDTO(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 1", BigDecimal.valueOf(10.00), null, null, 1));
        ProductMessage productMessage = Mapper.mapToProductMessage(product, 2, cartId);
        productConsumer.receiveProductMessage(productMessage);
        verify(cartService, times(1)).addProduct(any(ProductDTO.class));
    }

    @Test
    public void testReceiveRemoveProductMessage() {
        ProductDTO product = Mapper.toProductDTO(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 1", BigDecimal.valueOf(10.00), null, null, 1));
        ProductMessage productMessage = Mapper.mapToProductMessage(product, 2, cartId);
        productConsumer.receiveRemoveProductMessage(productMessage);
        verify(cartService, times(1)).removeProduct(eq(cartId), eq(product.getProductID().toString()));
    }

    @Test
    public void testReceiveUpdateProductMessage() {
        ProductDTO product = Mapper.toProductDTO(new Product(new ProductID(UUID.randomUUID(), cartId), "Product 1", BigDecimal.valueOf(10.00), null, null, 1));
        ProductMessage productMessage = Mapper.mapToProductMessage(product, 2, cartId);
        productConsumer.receiveUpdateProductMessage(productMessage);
        verify(cartService, times(1)).updateProduct(eq(cartId), eq(product.getProductID().toString()), eq(2));
    }

}