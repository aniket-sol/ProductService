package org.example.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String imageUrl;
}
/*
Cardinality
P C
1 1 -> m : 1
m 1

Foreign key constraints
fk constraints

create product with a certain category
delete category =>
    1. delete all associated products
    2. don't allow to delete this category
    3. update category_id of all associated products to NULL

 Product
 category_id

 category_id = 1
 used in 100 rows in product tables
 */