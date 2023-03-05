package com.skypro.library.dao;

import com.skypro.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDaoImpl(@Lazy JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
       return new JdbcTemplate(dataSource);
    }

    @Override
    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO book VALUES (?,?,?,?)", book.getName(), book.getAuthor(), book.getYearPublication(), book.getIsbn());
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE book SET name = ?, author = ?, year_publication = ? WHERE isbn = ?", book.getName(), book.getAuthor(), book.getYearPublication(), book.getIsbn());
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        jdbcTemplate.update("DELETE FROM book WHERE isbn = ?", isbn);
    }

    private final RowMapper<Book> actorRowMapper = (resultSet, rowNum) -> {
        Book book = new Book();
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearPublication(Integer.valueOf(resultSet.getString("year_publication")));
        book.setIsbn(resultSet.getString("isbn"));
        return book;
    };

    @Override
    public List<Book> readAllBook() {
        return jdbcTemplate.query("SELECT * FROM book", actorRowMapper);
    }

    @Override
    public Book readBookByIsbn(String isbn) {
        return jdbcTemplate.query("SELECT * FROM book WHERE isbn = ?", new Object[]{isbn}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
}


//Для пула.
