package com.cydeo.lab8restecommerce.controller;

import com.cydeo.lab8restecommerce.dto.ProductDTO;
import com.cydeo.lab8restecommerce.dto.ProductRequest;
import com.cydeo.lab8restecommerce.model.ResponseWrapper;
import com.cydeo.lab8restecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController // rest api endpoints
@RequestMapping("/api/v1/product")//class level ->common for all endpoints
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping// in responseEntity we can manage what we are going to return
    public ResponseEntity<ResponseWrapper> getProductList(){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved",
                productService.retrieveProductList(), HttpStatus.OK));


    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(new ResponseWrapper("Product is Successfully retrieved",
                productService.updateProduct(productDTO),HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok( new ResponseWrapper("Product is successfully created",
                productService.createProduct(productDTO), HttpStatus.OK));
    }
    @PostMapping("/categoryandprice")
    public ResponseEntity<ResponseWrapper> retrieveProductByCategoryAndPrice(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved by category and price",
                productService.retrieveAllProductByCategoryAndPrice(productRequest.getCategoryList(), productRequest.getPrice()),HttpStatus.OK));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> retrieveProductByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Product is successfully retrieved by name",
                productService.retrieveByName(name), HttpStatus.OK ));
    }
    @GetMapping("/top3")
    public ResponseEntity<ResponseWrapper> retrieveProductByTop3ProductByPrice(){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved",
                productService.retrieveProductByTop3ProductPrice(),HttpStatus.OK));
    }
    @GetMapping("/price/{price}")
    public ResponseEntity<ResponseWrapper> countProductsByPrice(@PathVariable("price")BigDecimal price){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved",
                productService.countProductByPrice(price),HttpStatus.OK));

    }





}
