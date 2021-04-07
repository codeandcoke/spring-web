package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    Book findBook(long id);
    List<Book> findAllBooks();
    List<Book> findAllBooks(String category);
}
