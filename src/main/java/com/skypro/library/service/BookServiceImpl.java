package com.skypro.library.service;

import com.skypro.library.dao.BookDao;
import com.skypro.library.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public List<Book> getBook() {
        return bookDao.readAllBook();
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        validateBook(book);
        bookDao.createBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Book updateBook = bookDao.readBookByIsbn(book.getIsbn());
        if(updateBook == null) {
            throw new NullPointerException();
        }
        updateBook.setName(book.getName());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setYearPublication(book.getYearPublication());
        validateBook(updateBook);
        bookDao.updateBook(book);
    }


    @Override
    @Transactional
    public void deleteBookByIsbn(String isbn) {
        Book book = bookDao.readBookByIsbn(isbn);
        if(book == null) {
            throw new NullPointerException();
        }
        bookDao.deleteBookByIsbn(isbn);
    }

    private boolean validateBook(Book book) {
        if (book.getName() == null || book.getAuthor() == null || book.getYearPublication() == null || book.getIsbn() == null || book.getYearPublication() < 0) {
            throw new NullPointerException();
        }

        String curIsbn = book.getIsbn();
        String cleanIsbn = curIsbn.replaceAll("[\\-\\s]", "");

        if (cleanIsbn.length() != 13 && !cleanIsbn.matches("[0-9] +")) {
            throw new IllegalArgumentException();
        }


        int a = 0;
        for (int i = 0; i < cleanIsbn.length(); i++) {
            int b = Character.getNumericValue(cleanIsbn.charAt(i));
            a = a + ((i % 2 ==0) ? b : b * 3);
        }

        int c = 10 - (a % 10);
        return c == Character.getNumericValue(cleanIsbn.charAt(cleanIsbn.length() - 1));
    }
}
