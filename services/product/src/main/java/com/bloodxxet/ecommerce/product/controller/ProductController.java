package com.bloodxxet.ecommerce.product.controller;

import com.bloodxxet.ecommerce.product.dto.ProductRequest;
import com.bloodxxet.ecommerce.product.dto.ProductPurchaseRequest;
import com.bloodxxet.ecommerce.product.dto.ProductPurchaseResponse;
import com.bloodxxet.ecommerce.product.dto.ProductResponse;
import com.bloodxxet.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/")
    public ResponseEntity<Long> createProductHandler(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProductsHandler(
            @RequestBody List<ProductPurchaseRequest> requests
    ) {
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> getProductHandler(
            @PathVariable("product_id") Long productId
    ) {
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> findAllProductsHandler() {
        return ResponseEntity.ok(service.findAllProducts());
    }
}
