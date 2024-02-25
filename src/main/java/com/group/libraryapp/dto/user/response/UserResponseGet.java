package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.User;

public class UserResponseGet {
    private long id;
    private String name;
    private Integer age;

    public UserResponseGet(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
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
