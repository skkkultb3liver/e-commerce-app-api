package com.bloodxxet.ecommerce.product.mapper;

import com.bloodxxet.ecommerce.product.dto.ProductPurchaseResponse;
import com.bloodxxet.ecommerce.product.dto.ProductRequest;
import com.bloodxxet.ecommerce.product.dto.ProductResponse;
import com.bloodxxet.ecommerce.product.entity.Category;
import com.bloodxxet.ecommerce.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProductEntity(ProductRequest request) {

        if (request == null) return null;

        Product product = Product.builder()
                .id(request.id())
                .title(request.title())
                .price(request.price())
                .description(request.description())
                .quantity(request.quantity())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();

        return product;

    }

    public ProductResponse toProductResponse(Product product) {

        if (product == null) return null;

        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                product.getCategory().getSlug()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Integer quantity) {

        return new ProductPurchaseResponse(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }

}
