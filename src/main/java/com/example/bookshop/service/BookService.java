package com.example.bookshop.service;

import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> findAll() { return repo.findAll(); }

    public Optional<Book> findById(Long id) { return repo.findById(id); }

    public List<Book> findByTitle(String title) { return repo.findByTitleContaining(title); }

    public List<Book> findByPriceLessThanEqual(int price) { return repo.findByPriceLessThanEqual(price); }

    public Book save(Book book) { return repo.save(book); }

    public void delete(Long id) { repo.deleteById(id); }
}