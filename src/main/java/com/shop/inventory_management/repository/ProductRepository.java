package com.shop.inventory_management.repository;

import com.shop.inventory_management.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
