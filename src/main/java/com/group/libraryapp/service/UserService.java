package com.group.libraryapp.service;

import com.group.libraryapp.dto.request.UserRequestCreate;

public interface UserService {

    void saveUser(UserRequestCreate request);

}
