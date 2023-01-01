package com.bitsmilez.cartmicroservice.core.domain.service.interfaces;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {
}
