package com.Order_Service.Resilience;

@Component
public class ProductFeignFallbackFactory implements FallbackFactory<ProductFeignClient> {
    @Override
    public ProductFeignClient create(Throwable cause) {
        return id -> new ProductDto(-1L, "UNKNOWN", "Service unavailable", 0.0);
    }
}