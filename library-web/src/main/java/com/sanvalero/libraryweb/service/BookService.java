package com.sanvalero.libraryweb.service;

import com.sanvalero.libraryweb.domain.Book;

import java.util.List;

public interface BookService {

    Book findBook(long id);
    List<Book> findAllBooks();
    List<Book> findAllBooks(String category);
}
