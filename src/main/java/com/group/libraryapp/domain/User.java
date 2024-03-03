package com.group.libraryapp.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "name", nullable = false, length = 25)
    private String name; // 필드랑 db 컬럼명이 같은 경우, name 속성 생략 가능
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories  = new ArrayList<>();


    protected User(){}

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) // isBlank() == (isEmpty() && "")
            throw new IllegalArgumentException(String.format("이름(name(%s))이 잘못 들어갔습니다.", name));
        this.name = name;
        this.age = age;
    }

    public void updateName(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void checkoutBook(Book book) {
        this.userLoanHistories.add(new UserLoanHistory(this, book.getName()));
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<UserLoanHistory> getUserLoanHistories() {
        return userLoanHistories;
    }
}
