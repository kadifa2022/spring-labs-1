package com.cydeo.lab08apipractice.service.serviceImp;


import com.cydeo.lab08apipractice.mapper.MapperUtil;
import com.cydeo.lab08apipractice.repository.ProductRepository;
import com.cydeo.lab08apipractice.service.CategoryService;
import com.cydeo.lab08apipractice.service.ProductService;
import com.cydeo.lab08apipractice.dto.ProductDTO;
import com.cydeo.lab08apipractice.entity.Product;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final CategoryService categoryService;//

    public ProductServiceImp(ProductRepository productRepository, MapperUtil mapperUtil, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.categoryService = categoryService;
    }


    @Override
    public List<ProductDTO> retrieveListProduct() {
        return productRepository.findAll().stream()
                .map(product ->mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductByCategoryAndPrice(List<Long> categoryList, BigDecimal price) {
        return productRepository.retrieveProductListByCategory(categoryList, price).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO retrieveProductByName(String name) {
        return mapperUtil.convert(productRepository.findFirstByName(name), new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductByTop3ProductByPrice() {
        return productRepository.findTop3ByOrderByPriceDesc().stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer countProductByPrice(BigDecimal price) {
        return productRepository.countProductByPriceGreaterThan(price);
    }

    @Override
    public List<ProductDTO> retrieveProductByPriceAndQuantity(BigDecimal price, Integer quantity) {
        return productRepository.retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(price, quantity)
                .stream().map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> retrieveProductByCategoryId(Long categoryId) {
        return productRepository.retrieveProductListByCategory(categoryId)
                .stream().map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }





}
























