package com.cydeo.lab8restecommerce.service.impl;

import com.cydeo.lab8restecommerce.dto.ProductDTO;
import com.cydeo.lab8restecommerce.entity.Product;
import com.cydeo.lab8restecommerce.mapper.MapperUtil;
import com.cydeo.lab8restecommerce.repository.ProductRepository;
import com.cydeo.lab8restecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    // implementation of our business logic
    //1. inject repository
    public  final ProductRepository productRepository;
    public final MapperUtil mapperUtil;// generic feature to convert anything

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> retrieveProductList() {
       List<Product > list = productRepository.findAll();// Need list of product from DB and return them as dto
       return  list.stream().map(product -> mapperUtil.convert(product, new ProductDTO()))
               .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {// taking the information from user converting and saving
        Product product = productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product, new ProductDTO()); // converting back to dto, we want to see updated value
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product,new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveAllProductByCategoryAndPrice(List<Long> categoryList, BigDecimal price) {
        return productRepository.retrieveProductListByCategory(categoryList,price).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());

    }

    @Override
    public ProductDTO retrieveByName(String name) {
        return mapperUtil.convert(productRepository.findFirstByName(name),new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductByTop3ProductPrice() {
        return productRepository.findTop3ByOrderByPriceDesc().stream()
                .map(product -> mapperUtil.convert(product,new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer countProductByPrice(BigDecimal price) { // we are returning this one as number
        return productRepository.countProductByPriceGreaterThan(price);
    }


}
