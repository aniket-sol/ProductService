package org.example.productservice.services;

import org.example.productservice.dtos.CreateProductRequestDto;
import org.example.productservice.dtos.FakeStoreProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate; //using this, will be able to call 3rd party apis
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts(int limit) {
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();

        if (fakeStoreProducts == null) throw new AssertionError();

        for (FakeStoreProductDto dto : fakeStoreProducts) {
            products.add(dto.toProduct());
        }
        return products.size() > limit ? products.subList(0, limit) : products;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
//      call external fakestore api
        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(fakeStoreProduct == null) {
            throw new ProductNotFoundException("Product with id " + id + " is not available");
        }
        return fakeStoreProduct.toProduct();
    }
    public Product createProduct(String title, String description, String category, String image, double price){
        FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();
        fakeStoreProduct.setTitle(title);
        fakeStoreProduct.setDescription(description);
        fakeStoreProduct.setCategory(category);
        fakeStoreProduct.setImage(image);
        fakeStoreProduct.setPrice(price);

        FakeStoreProductDto fakeStoreProduct1 = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDto.class);
        return fakeStoreProduct1.toProduct();
    }
}
