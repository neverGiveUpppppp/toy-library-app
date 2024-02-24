package com.group.libraryapp.service;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.request.UserRequestCreate;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void saveUser(UserRequestCreate request) {
        repository.save(new User(request.getName(),request.getAge()));
    }


}
