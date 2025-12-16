package com.Order_Service.Client;

@FeignClient(name = "product-service", url = "http://product-service:8081", fallbackFactory = ProductFeignFallbackFactory.class)
public interface ProductFeignClient {
    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);
}