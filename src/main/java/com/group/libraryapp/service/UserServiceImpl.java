package com.group.libraryapp.service;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.user.request.UserRequestCreate;
import com.group.libraryapp.dto.user.request.UserRequestUpdate;
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


    @Transactional
    public void saveUser(UserRequestCreate request) {
        repository.save(new User(request.getName(),request.getAge()));
    }

    @Transactional(readOnly = true)  // readOnly = true : 성능상 소폭 이득
    public List<UserResponseGet> getUser() {
        return repository.findAll().stream()
                .map(UserResponseGet::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserRequestUpdate request) {
        // update할 유저 찾기
        User user = repository.findById(request.getId())  // findById() : select * from user whwere id = ?; JPA가 자동생성 // 반환값 : Optional<User>
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getId(),request.getName());  // update sql를 바로 호출하는 게 아닌 User객체를 최신화해주고 User객체를 파라미터로해서 save()로 Update 진행
//        repository.save(user); // 영속성 컨텍스트의 변경감지(dirty check) 기능으로 생략가능 // 영속성 컨텍스트 사용을 위해 @Transactional 추가 필수(없다면 save메소드 필수)
    }

}
