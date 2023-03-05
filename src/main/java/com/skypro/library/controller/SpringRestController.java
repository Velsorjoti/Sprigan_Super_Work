package com.skypro.library.controller;

import com.skypro.library.model.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

@RestController
@RequestMapping("/a")
public class SpringRestController {
    private BookService bookService;

    public SpringRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getBook () {
        return bookService.getBook();
    }

    @PostMapping("/api/book")
    public Book addBook(@RequestBody Book book) {

        bookService.addBook(book);
        return book;

    }

    @PutMapping("/api/book")
    public Book updateBook(@RequestBody Book book) {

        bookService.updateBook(book);
        return book;

    }

    @DeleteMapping("/api/book")
    public String deleteBook(@RequestParam String isbn) {
       bookService.deleteBookByIsbn(isbn);
        return "Book with isbn = " + isbn + " was deleted";

    }
}

//Для пула.
