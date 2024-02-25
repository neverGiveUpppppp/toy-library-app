package com.group.libraryapp.service;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.user.request.UserRequestCreate;
import com.group.libraryapp.dto.user.response.UserResponseGet;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)  // readOnly = true : 성능상 소폭 이득
    public void saveUser(UserRequestCreate request) {
        repository.save(new User(request.getName(),request.getAge()));
    }

    @Transactional
    public List<UserResponseGet> getUser() {
        return repository.findAll().stream()
                .map(UserResponseGet::new)
                .collect(Collectors.toList());
    }


}
