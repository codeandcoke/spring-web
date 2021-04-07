package com.sanvalero.libraryweb.service;

import com.sanvalero.libraryweb.domain.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Book findBook(long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> response = restTemplate.getForEntity("http://localhost:8081/books/" + id, Book.class);
        return response.getBody();
    }

    @Override
    public List<Book> findAllBooks() {
        RestTemplate restTemplate = new RestTemplate();
        Book[] arrayBooks = restTemplate.getForObject("http://localhost:8081/books", Book[].class);
        if (arrayBooks == null)
            return Collections.emptyList();

        List<Book> books = Arrays.asList(arrayBooks);
        return books;
    }

    @Override
    public List<Book> findAllBooks(String category) {
        return null;
    }
}
