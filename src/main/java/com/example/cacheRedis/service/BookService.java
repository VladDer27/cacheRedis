package com.example.cacheRedis.service;

import com.example.cacheRedis.entity.Book;
import com.example.cacheRedis.entity.Category;
import com.example.cacheRedis.repository.BookRepository;
import com.example.cacheRedis.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(value = "books", key = "#title + #author")
    public Optional<Book> findBookByTitleAndAuthor(String title, String author) {
        System.out.println("Выполняется запрос к БД по поиску книг по авторам и названиям");
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @Cacheable(value = "booksByCategory", key = "#categoryName")
    public List<Book> findBooksByCategoryName(String categoryName) {
        System.out.println("Выполняется запрос к БД по поиску кинг по категориям");
        return bookRepository.findByCategoryName(categoryName);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, allEntries = true)
    public Book createBook(Book book) {
        Category category = categoryRepository.findByName(book.getCategory().getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(book.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        book.setCategory(category);
        return bookRepository.save(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, allEntries = true)
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        Category category = categoryRepository.findByName(bookDetails.getCategory().getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(bookDetails.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        book.setCategory(category);
        return bookRepository.save(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, allEntries = true)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
