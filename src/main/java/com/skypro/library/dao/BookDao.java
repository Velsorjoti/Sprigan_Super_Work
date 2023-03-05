package com.skypro.library.dao;

import com.skypro.library.model.Book;

import java.util.List;

public interface BookDao {
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBookByIsbn(String isbn);
    List<Book> readAllBook();
    Book readBookByIsbn(String isbn);

}

//Для пула.
