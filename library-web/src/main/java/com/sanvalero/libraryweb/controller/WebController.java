package com.sanvalero.libraryweb.controller;

import com.sanvalero.libraryweb.domain.Book;
import com.sanvalero.libraryweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WebController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/catalog", method = GET)
    public String catalog(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "catalog";
    }

    @RequestMapping(value = "/book/{id}", method = GET)
    public String book(Model model, @PathVariable("id") long id) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "book";
    }
}
