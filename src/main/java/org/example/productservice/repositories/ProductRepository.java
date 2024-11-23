package org.example.productservice.repositories;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findById(long id);
    List<Product> findAll();
    List<Product> findByCategory(Category category);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findAllByCategoryName(String categoryName);

    @Query("select p.title as title, p.id as id from Product p where p.category.name = :categoryName")
        List<ProductProjection> getTitleAndIdOfAllProductsWithCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "Select p.title as title, p.id as id from products p join category c on p.category_id = c.id where c.name = :categoryName", nativeQuery = true)
        List<ProductProjection> getTitlesAndIdOfAllProductsWithCategoryNameEquals(@Param("categoryName") String categoryName);
}
