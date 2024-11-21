package org.example.productservice.services;

import org.example.productservice.dtos.CreateProductRequestDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(int limit);
    Product getProductById(long id) throws ProductNotFoundException;
    Product createProduct(String title,
                          String description,
                          String category,
                          String image,
                          double price);
}
