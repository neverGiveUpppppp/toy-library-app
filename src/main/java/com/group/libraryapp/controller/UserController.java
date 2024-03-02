package com.group.libraryapp.controller;

import com.group.libraryapp.dto.user.request.UserRequestCreate;
import com.group.libraryapp.dto.user.request.UserRequestUpdate;
import com.group.libraryapp.dto.user.response.UserResponseGet;
import com.group.libraryapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user")
    public List<UserResponseGet> getUsers() {
        return service.getUser();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserRequestUpdate request) {
        service.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        service.deleteUser(name);
    }

}
