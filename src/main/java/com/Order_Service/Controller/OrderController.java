package com.Order_Service.Controller;

import com.Order_Service.Client.ProductFeignClient;
import com.Order_Service.Entity.Order;
import com.Order_Service.Model.ProductDto;
import com.Order_Service.Repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    private final ProductFeignClient productFeignClient;


    public OrderController(OrderRepository repo, ProductFeignClient productFeignClient){this.repo = repo; this.productFeignClient = productFeignClient;}


    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest req){
        ProductDto p = productFeignClient.getProductById(req.getProductId());
        if (p.getId() == -1L) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Order order = new Order();
        order.setProductId(p.getId());
        order.setQuantity(req.getQuantity());
        order.setTotalPrice(p.getPrice() * req.getQuantity());
        repo.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


// GET /api/orders, GET /api/orders/{id}, PUT, DELETE similar to product-service
}