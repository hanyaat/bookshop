package com.example.bookshop.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.bookshop.model.Cart;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.CartRepository;
import com.example.bookshop.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;

    public OrderService(OrderRepository orderRepo,CartRepository cartRepo){
        this.orderRepo=orderRepo;
        this.cartRepo=cartRepo;
    }

    //カートの中身をもとに注文を作成する
    @Transactional
    public Order placeOrder(User user){
        List<Cart> cartItems=cartRepo.findByUserId(user.getId());

        Order order=new Order();
        order.setUser(user);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderedAt(LocalDateTime.now());

        List<OrderItem> items=new ArrayList<>();
        int total=0;

        for(Cart cart : cartItems){
            OrderItem item=new OrderItem();
            item.setOrder(order);
            item.setBook(cart.getBook());
            item.setQuantity(cart.getQuantity());
            item.setUnitPrice(cart.getBook().getPrice());
            total +=cart.getBook().getPrice() *cart.getQuantity();
            items.add(item);
        }

        order.setOrderItems(items);
        order.setTotalPrice(total);

        Order saved=orderRepo.save(order);

        //注文完了後カートを空にする
        cartRepo.deleteAll(cartItems);

        return saved;
    }

    public List<Order> findByUserId(Long userId){
        return orderRepo.findByUserId(userId);
    }

    public Optional<Order> findById(Long id){
        return orderRepo.findById(id);
    }
}
