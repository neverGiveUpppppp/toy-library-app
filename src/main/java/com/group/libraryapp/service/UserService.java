package com.group.libraryapp.service;

import com.group.libraryapp.dto.user.request.UserRequestCreate;
import com.group.libraryapp.dto.user.response.UserResponseGet;

import java.util.List;

public interface UserService {

    void saveUser(UserRequestCreate request);

    List<UserResponseGet> getUser();
}
