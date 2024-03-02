package com.group.libraryapp.controller;

import com.group.libraryapp.dto.book.BookRequestCreate;
import com.group.libraryapp.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookRequestCreate request) {
        service.saveBook(request);
    }


}
