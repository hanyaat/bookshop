package com.example.bookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // ユーザーIDでカートの中身を取得
    List<Cart> findByUserId(Long userId);

    // カートから特定の本を削除するために検索
    void deleteByUserIdAndBookId(Long userId, Long bookId);
}