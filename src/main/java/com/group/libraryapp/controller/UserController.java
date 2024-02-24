package com.group.libraryapp.controller;

import com.group.libraryapp.dto.request.UserRequestCreate;
import com.group.libraryapp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserRequestCreate request) {
        service.saveUser(request);
    }


}
