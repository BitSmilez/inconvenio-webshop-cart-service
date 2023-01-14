package com.bitsmilez.cartmicroservice.core.domain.service.impl;

import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.CartDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    IProductRepository productRepository;

    public CartServiceImpl(IProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }


    @Override
    public CartDTO getProducts(String cartID) {
        List<ProductDTO> products = productRepository.findByProductIDCartID(cartID).stream().map(Mapper::toProductDTO).toList();
        if ( products.size()!=0){
            CartDTO cartDTO = new CartDTO(cartID);
            cartDTO.setItems(products);
            return cartDTO;
        }
        else{
            return null;
        }
    }

    @Override
    public void addProduct(ProductDTO product) {
        ProductID id = new ProductID( product.getProductID(), product.getCartID());
        productRepository.findById(id).ifPresentOrElse(
                (p) -> {
                    p.setQuantity(p.getQuantity() + product.getQuantity());
                    productRepository.save(p);
                },
                () -> productRepository.save(Mapper.toProduct(product))
        );
    }

    @Override
    public void removeProduct(String cartID, String productID) {
        ProductID id = new ProductID(UUID.fromString(productID), cartID);
        productRepository.findById(id).ifPresent(
                (p) -> productRepository.delete(p)
        );
    }

    @Override
    public void updateProduct(String cartID, String productID, int quantity) {
        ProductID id = new ProductID(UUID.fromString(productID), cartID);
        productRepository.findById(id).ifPresent(
                (p) -> {
                    p.setQuantity(quantity);
                    productRepository.save(p);
                }
        );
    }
}



