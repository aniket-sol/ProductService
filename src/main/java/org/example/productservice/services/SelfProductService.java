package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(int limit) {
        return List.of();
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product createProduct(String title, String description, String category, String image, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDB = categoryRepository.findByName(category);
        if(categoryFromDB == null) {
            categoryFromDB = new Category();
            categoryFromDB.setName(category);
        }
        product.setCategory(categoryFromDB);

        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }
}