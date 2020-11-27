package com.ecommerce.eccomApp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // extends CrudRepository


    // Returns a List of All Products that is currently in the database
    public List<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        for(Product prod : productRepository.findAll()) {
            productList.add(prod);
            System.out.println(prod.getName());
        }
        return productList;
    }

    // Returns a single Product if it Exist or returns NULL
    public Product getProduct(Long Id) {
        return productRepository.findById(Id).orElse(null);
    }
}