package com.example.bookshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookshop.model.Cart;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CartService;
import com.example.bookshop.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final BookService bookService;

    public CartController(CartService cartService,UserService userService,BookService bookService){
        this.cartService=cartService;
        this.userService=userService;
        this.bookService=bookService;
    }

    //カートの中身を確認
    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId){
        return cartService.getCartItems(userId);
    }

    //カートに追加
    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Map<String,Object> body){
        Long userId = Long.valueOf(body.get("userId").toString());
        Long bookId = Long.valueOf(body.get("bookId").toString());
        int quantity = Integer.parseInt(body.get("quantity").toString());

        return userService.findById(userId).flatMap(user -> 
            bookService.findById(bookId).map(book ->
                ResponseEntity.ok(cartService.addToCart(user,book,quantity))
            )
        ).orElse(ResponseEntity.notFound().build());    
    }
    

    //カートから削除
    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<Void> removeFromCart(
        @PathVariable Long userId,
        @PathVariable Long bookId){
            cartService.removeFromCart(userId,bookId);
            return ResponseEntity.noContent().build();
        }
}
