package com.felixmcd.productservice.service;

import com.felixmcd.productservice.dto.ProductRequest;
import com.felixmcd.productservice.dto.ProductResponse;
import com.felixmcd.productservice.model.Product;
import com.felixmcd.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j //log
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //constucter for abovce to work ^^ can be replaced with lombock's @RequiredArgsConstructor

//    public ProductService(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        //java 8 streams map
        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
