package com.example.cacheRedis.web.controller;

import com.example.cacheRedis.entity.Book;
import com.example.cacheRedis.entity.Category;
import com.example.cacheRedis.service.BookService;
import com.example.cacheRedis.web.model.BookRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{title}/{author}")
    public Optional<Book> getBookByTitleAndAuthor(@PathVariable String title, @PathVariable String author) {
        return bookService.findBookByTitleAndAuthor(title, author);
    }

    @GetMapping("/category/{name}")
    public List<Book> getBooksByCategoryName(@PathVariable String name) {
        return bookService.findBooksByCategoryName(name);
    }

    @PostMapping
    public Book createBook(@RequestBody BookRequest request) {
        Book newBook = new Book();
        newBook.setTitle(request.getTitle());
        newBook.setAuthor(request.getAuthor());
        Category category = new Category();
        category.setName(request.getCategory());
        newBook.setCategory(category);
        return bookService.createBook(newBook);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        Book newBook = new Book();
        newBook.setTitle(request.getTitle());
        newBook.setAuthor(request.getAuthor());
        Category category = new Category();
        category.setName(request.getCategory());
        newBook.setCategory(category);
        return bookService.updateBook(id, newBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
