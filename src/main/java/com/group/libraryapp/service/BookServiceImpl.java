package com.group.libraryapp.service;

import com.group.libraryapp.domain.Book;
import com.group.libraryapp.domain.User;
import com.group.libraryapp.domain.UserLoanHistory;
import com.group.libraryapp.dto.book.BookRequestCheckout;
import com.group.libraryapp.dto.book.BookRequestCreate;
import com.group.libraryapp.dto.book.BookRequestReturn;
import com.group.libraryapp.repository.UserLoanHistoryRepository;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public void returnBook(BookRequestReturn requestRetn){
        // 책 반납
        // 유저가 빌린 책 // 유저 대여 기록 // 유저대여기록 도메인객체에서 기록 찾아보고 반환하기
        // 해당 유저가 빌린 책 중 특정 책 반환이기에 유저에서 유저대여기록을 찾아들어가 대여여부인 is_returned을 변경 // list수정인 .set()이 index를 요구되어 index를 구해오기가 까다로움
//        boolean isLoaned = loanHistoryRepo.existsByBookNameAndIsReturned(requestRetn.getBookName(), false);
//        if (isLoaned){
//            User user = userRepo.findByName(requestRetn.getUserName())
//                    .orElseThrow(IllegalArgumentException::new);
//            user.returnBook(requestRetn.getBookName());
//        }

        // 반납 시 해야할 것
        //    1)userId를 찾아서 가져오기
        //    2)userId와 bookname으로 대출 기록 찾기
        User user = userRepo.findByName(requestRetn.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        UserLoanHistory loanHistory = loanHistoryRepo.findByUserIdAndBookName(user.getId(), requestRetn.getBookName()).stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        loanHistory.returnBook();
//        loanHistoryRepo.save(loanHistory); // 영속성 컨텍스트 변경감지(dirty check) 작동으로 자동저장
    }

}
