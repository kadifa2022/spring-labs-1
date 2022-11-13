package com.cydeo.lab07ormqueries.repository;

import com.cydeo.lab07ormqueries.Entity.Category;
import com.cydeo.lab07ormqueries.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Write a derived query to get top 3 product order by price desc

    List<Product> findTop3ByOrderByPriceDesc();
    //Write a derived query to get product by specific name
     Product findFirstByName(String name);

    //Write a derived query to get product by specific category
    List<Product> findAllByCategoryListContaining(Category category);
    //Write a derived query to get count by price greater than specific amount
    Integer countProductByPriceGreaterThan(BigDecimal price);

    //Write a derived query to get all product by quantity greater than or equal specific count
    //Write a native query to get all product by price greater than specific amount and quantity lower than specific count
    //Write a native query to get all product by specific categoryId
    //Write a native query to get all product by specific categoryId and price greater than specific amount
}
