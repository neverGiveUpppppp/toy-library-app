package com.group.libraryapp.service;

import com.group.libraryapp.domain.Book;
import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.book.BookRequestCheckout;
import com.group.libraryapp.dto.book.BookRequestCreate;
import com.group.libraryapp.repository.UserLoanHistoryRepository;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepo;
    private final UserLoanHistoryRepository loanHistoryRepo;
    private final UserRepository userRepo;

    public BookServiceImpl(BookRepository repository, UserLoanHistoryRepository loanHistoryRepo, UserRepository userRepo) {
        this.bookRepo = repository;
        this.loanHistoryRepo = loanHistoryRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void saveBook(BookRequestCreate request) {
        bookRepo.save(new Book(request.getName()));
    }

    @Transactional
    public void checkoutBook(BookRequestCheckout requestChkout) {
        // 책 대출
        // 빌려줄 책이 있는 지 확인(대출 기록 확인?)
        //      대출기록 확인에 필요한 userId, bookname 끌어오기
        // 대출 실행 - user가 book을 빌려가는 것임
        Book book = bookRepo.findByName(requestChkout.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        boolean isLoaned = loanHistoryRepo.existsByBookNameAndIsReturned(book.getName(), false);
        if(isLoaned)
            throw new IllegalArgumentException("이미 대출 중입니다.");
        User user = userRepo.findByName(requestChkout.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.checkoutBook(book);
    }

}
