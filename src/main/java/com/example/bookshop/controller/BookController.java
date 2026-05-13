package com.example.bookshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookshop.model.Book;
import com.example.bookshop.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service){
        this.service=service;
    }

    //全休取得
    @GetMapping
    public List<Book> getAll(){
        return service.findAll();
    }

    //1件取得
    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable Long id){
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

        //タイトルで検索
    @GetMapping("/search")
    public List<Book> search(@RequestParam String title){
        return service.findByTitle(title);
    }

    //価格以下で検索
    @GetMapping("/price")
    public List<Book> searchByPrice(@RequestParam int max){
        return service.findByPriceLessThanEqual(max);
    }

    //登録
    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book){
        return ResponseEntity.ok(service.save(book));
    }

    //更新
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,@Valid @RequestBody Book book){
        return service.findById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setPublishedYear(book.getPublishedYear());
            existing.setDescription(book.getDescription());
            existing.setPageCount(book.getPageCount());
            existing.setPrice(book.getPrice());
            existing.setStock(book.getStock());
            return ResponseEntity.ok(service.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    //削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
