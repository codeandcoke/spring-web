package com.codeandcoke.library.controller;

import com.codeandcoke.library.exception.BookNotFoundException;
import com.codeandcoke.library.exception.ErrorResponse;
import com.codeandcoke.library.domain.Book;
import com.codeandcoke.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
//    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @GetMapping(value = {"/books/{bookId}/authors"})
    public String getAuthors(@PathVariable(required = false) String bookId) {
        if (bookId == null)
            return "no bookId";

        return "authors";
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> modifyBook(@PathVariable long id, @RequestBody Book book) throws BookNotFoundException {
        Book newBook = bookService.editBook(id, book);
        return ResponseEntity.ok(newBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) throws BookNotFoundException {
        bookService.removeBook(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBooktNotFoundException(BookNotFoundException bnfe) {
        ErrorResponse errorResponse = new ErrorResponse(100, bnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
