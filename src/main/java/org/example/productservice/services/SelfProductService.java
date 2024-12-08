package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.projections.ProductProjection;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(int limit) {
        List<Product> products = productRepository.findAll();
//        List<ProductProjection> products_1 = productRepository.getTitleAndIdOfAllProductsWithCategoryName("Electronics");
//        System.out.println(products_1.get(0).getTitle());
//        System.out.println(products_1.get(0).getId());
//        System.out.println(products_1.get(1).getId());
//        System.out.println(products_1.get(1).getTitle());
//        System.out.println(products_1.get(0).getName());
//        System.out.println(products_1.get(0).getName());
//        System.out.println(products.size());
        return products;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id);
        if(product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product;
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
