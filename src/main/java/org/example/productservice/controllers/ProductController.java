package org.example.productservice.controllers;

import org.example.productservice.dtos.CreateProductRequestDto;
import org.example.productservice.dtos.ErrorDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //    at the end of the day, API = method in my controller
    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(value="limit", required = false, defaultValue = "0") int limit){
//        System.out.println(limit);
        return productService.getAllProducts(limit);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequest){
//        return productService.createProduct(createProductRequestDto);
        return productService.createProduct(
                createProductRequest.getTitle(),
                createProductRequest.getDescription(),
                createProductRequest.getCategory(),
                createProductRequest.getImage(),
                createProductRequest.getPrice()
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

}
