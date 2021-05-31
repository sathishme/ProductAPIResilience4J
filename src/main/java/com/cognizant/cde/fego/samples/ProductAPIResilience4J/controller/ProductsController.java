package com.cognizant.cde.fego.samples.ProductAPIResilience4J.controller;

import com.cognizant.cde.fego.samples.ProductAPIResilience4J.model.Product;
import com.cognizant.cde.fego.samples.ProductAPIResilience4J.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import brave.Tracer;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired()
    private Tracer tracer;

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public List<Product> getProducts() {
        return this.productsService.getProducts();
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        this.productsService.createProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        this.productsService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        this.productsService.deleteProduct(id);
    }
}
