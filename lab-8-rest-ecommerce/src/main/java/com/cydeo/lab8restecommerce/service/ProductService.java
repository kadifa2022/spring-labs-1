package com.cydeo.lab8restecommerce.service;

import com.cydeo.lab8restecommerce.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

   //Defining business logic we need inside service
    List<ProductDTO> retrieveProductList();
    ProductDTO updateProduct(ProductDTO productDTO);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> retrieveAllProductByCategoryAndPrice(List<Long> categoryList, BigDecimal price);

    ProductDTO retrieveByName(String name);

 List<ProductDTO> retrieveProductByTop3ProductPrice();

 Integer countProductByPrice(BigDecimal price);
}
