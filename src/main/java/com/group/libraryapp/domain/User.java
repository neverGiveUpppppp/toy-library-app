package com.group.libraryapp.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name; // 필드랑 db 컬럼명이 같은 경우, name 속성 생략 가능
    private Integer age;


    protected User(){}

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(String.format("이름(name(%s))이 잘못 들어갔습니다.", name));
        this.name = name;
        this.age = age;
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

}
