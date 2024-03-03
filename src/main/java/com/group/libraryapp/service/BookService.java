package com.group.libraryapp.service;

import com.group.libraryapp.dto.book.BookRequestCheckout;
import com.group.libraryapp.dto.book.BookRequestCreate;

public interface BookService {
    void saveBook(BookRequestCreate request);

    void checkoutBook(BookRequestCheckout requestCheckout);


}
