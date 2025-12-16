package com.Order_Service.Service;

import com.Order_Service.Client.ProductFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient {

    private final ProductServiceClient feign;

    public ProductServiceClient(ProductFeignClient feign) {
        this.feign = feign;
    }


    @CircuitBreaker(name = "productServiceCircuit", fallbackMethod = "fallbackGet")
    @Retry(name = "productServiceRetry")
    public ProductDto getProduct(Long id) { return feign.getProductById(id); }


    public ProductDto fallbackGet(Long id, Throwable t) {
        return new ProductDto(-1L, "UNKNOWN", "fallback", 0.0);
    }
}