package com.example.bookshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshop.model.Book;
import com.example.bookshop.model.Cart;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo){
        this.repo=repo;
    }

    //カートに追加
    public Cart addToCart(User user,Book book,int quantity){
        Cart cart=new Cart();
        cart.setUser(user);
        cart.setBook(book);
        cart.setQuantity(quantity);
        return repo.save(cart);
    }

    //カートの中身を取得
    public List<Cart>getCartItems(Long userId){
        return repo.findByUserId(userId);
    }

    //カートから削除
    @Transactional
    public void removeFromCart(Long userId,Long bookId){
        repo.deleteByUserIdAndBookId(userId,bookId);
    }
}
