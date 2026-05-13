package com.example.bookshop.controller;

import com.example.bookshop.model.Order;
import com.example.bookshop.service.OrderService;
import com.example.bookshop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // 注文
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok(orderService.placeOrder(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 注文履歴を取得 
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

    // 注文詳細を取得
    @GetMapping("/detail/{id}")
    public ResponseEntity<Order> getDetail(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}