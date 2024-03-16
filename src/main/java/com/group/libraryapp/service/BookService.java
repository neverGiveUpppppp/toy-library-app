package com.group.libraryapp.service;

import com.group.libraryapp.dto.book.BookRequestCheckout;
import com.group.libraryapp.dto.book.BookRequestCreate;
import com.group.libraryapp.dto.book.BookRequestReturn;

public interface BookService {
    void saveBook(BookRequestCreate request);

    void checkoutBook(BookRequestCheckout requestCheckout);

    void returnBook(BookRequestReturn requestRetn);

}
