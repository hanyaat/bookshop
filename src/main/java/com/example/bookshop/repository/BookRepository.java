package com.example.bookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.model.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

//タイトルで部分一致検索
List<Book> findByTitleContaining(String title);

//著者で検索
List<Book> findByAuthor(String author);

//価格以下の本を検索
List<Book> findByPriceLessThanEqual(int price);
    
}
