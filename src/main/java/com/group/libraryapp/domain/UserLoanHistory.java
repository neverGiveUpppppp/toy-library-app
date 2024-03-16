package com.group.libraryapp.domain;

import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private boolean isReturned;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;


    protected UserLoanHistory(){}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturned = false;
    }

    public void returnBook(){
        this.isReturned = true;
    }


    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public User getUser() {
        return user;
    }
}
