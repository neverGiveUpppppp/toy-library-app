package com.group.libraryapp.service;

import com.group.libraryapp.domain.Book;
import com.group.libraryapp.dto.book.BookRequestCreate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveBook(BookRequestCreate request) {
        repository.save(new Book(request.getName()));
    }

}
