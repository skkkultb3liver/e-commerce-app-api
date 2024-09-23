package com.bloodxxet.ecommerce.product.service.impl;


import com.bloodxxet.ecommerce.exception.ProductNotFoundException;
import com.bloodxxet.ecommerce.exception.ProductPurchaseException;
import com.bloodxxet.ecommerce.product.dto.ProductPurchaseRequest;
import com.bloodxxet.ecommerce.product.dto.ProductPurchaseResponse;
import com.bloodxxet.ecommerce.product.dto.ProductRequest;
import com.bloodxxet.ecommerce.product.dto.ProductResponse;
import com.bloodxxet.ecommerce.product.entity.Product;
import com.bloodxxet.ecommerce.product.mapper.ProductMapper;
import com.bloodxxet.ecommerce.product.repository.ProductRepository;
import com.bloodxxet.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Long createProduct(ProductRequest request) {

        var product = productMapper.toProductEntity(request);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) throws ProductPurchaseException {

        log.info("trying to purchase products: {}", requests);

        List<Long> productIds = requests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        log.info("product ids: {}", productIds);

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        log.info("found {} products", storedProducts.size());

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        var storedRequests = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var request = storedRequests.get(i);

            if (product.getQuantity() < request.quantity()) {
                throw new ProductPurchaseException("One or more products do not have enough quantity");
            }

            Integer newQuantity = product.getQuantity() - request.quantity();
            product.setQuantity(newQuantity);

            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, request.quantity()));
        }

        return purchasedProducts;
    }

    @Override
    public void updateProduct(ProductRequest request) {

    }

    @Override
    public List<ProductResponse> findAllProducts() {

        return productRepository.findAll()
                .stream().map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {

        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new ProductNotFoundException(String.format("Product not found with ID :: %s", id)));
    }

    @Override
    public List<ProductResponse> findProductsByQuery(String query) {
        return List.of();
    }

    @Override
    public List<ProductResponse> findProductsByCategories(List<Long> categoriesIds) {
        return List.of();
    }
}
