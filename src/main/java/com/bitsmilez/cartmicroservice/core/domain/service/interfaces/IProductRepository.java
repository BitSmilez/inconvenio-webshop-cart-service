package com.bitsmilez.cartmicroservice.core.domain.service.interfaces;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IProductRepository extends JpaRepository<Product, ProductID> {

    ArrayList<Product> findByProductIDCartID(String cartID);

}
