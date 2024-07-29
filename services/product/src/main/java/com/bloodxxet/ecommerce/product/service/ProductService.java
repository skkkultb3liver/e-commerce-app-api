package com.bloodxxet.ecommerce.product.service;

import com.bloodxxet.ecommerce.product.dto.ProductPurchaseRequest;
import com.bloodxxet.ecommerce.product.dto.ProductPurchaseResponse;
import com.bloodxxet.ecommerce.product.dto.ProductRequest;
import com.bloodxxet.ecommerce.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    Long createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests);

    void updateProduct(ProductRequest request);

    List<ProductResponse> findAllProducts();

    ProductResponse getProductById(Long id);

    List<ProductResponse> findProductsByQuery(String query);

    List<ProductResponse> findProductsByCategories(List<Long> categoriesIds);
}
