package com.cydeo.lab04springmvc.controller;


import com.cydeo.lab04springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    private final ProductService productService;//Injection

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //localhost:8080/search-product/milk
    @RequestMapping("/search-product/{productName}")
    public String searchProduct(@PathVariable("productName")String productName,Model model){

        model.addAttribute("productList", productService.searchProduct(productName));
        return"product/product-list";
    }

}
