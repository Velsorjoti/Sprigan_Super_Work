package com.skypro.library.model;


import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


public class Book {
    private String name;
    private String author;
    private Integer yearPublication;
    private String isbn;

    public Book() {
    }

    public Book(String name, String author, Integer yearPublication, String isbn) {
        this.name = name;
        this.author = author;
        this.yearPublication = yearPublication;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(Integer yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

//Для пула.
