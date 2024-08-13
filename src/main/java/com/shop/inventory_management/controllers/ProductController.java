package com.shop.inventory_management.controllers;


import com.shop.inventory_management.exception.ResourceNotFoundException;
import com.shop.inventory_management.models.Product;
import com.shop.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @CrossOrigin
    @GetMapping
    public List<Product> getAllProducts(){
        List<Product> getProducts = productRepository.findAll();
        if(getProducts.isEmpty()){
            throw new ResourceNotFoundException("productos");
        }
        return productRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new ResourceNotFoundException("producto", "id", id);
        }
        return product.map(ResponseEntity::ok).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        if(!productRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        if(!productRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
}
