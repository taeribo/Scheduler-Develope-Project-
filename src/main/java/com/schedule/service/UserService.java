package com.schedule.service;

import com.schedule.dto.*;
import com.schedule.entity.User;
import com.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 생성
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(savedUser);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(GetUserResponse::new)
                .toList();
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetUserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        return new GetUserResponse(user);
    }

    // 수정
    public UpdateUserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        user.update(request.getUsername(), request.getEmail());
        userRepository.save(user);
        return new UpdateUserResponse(user);
    }

    //삭제
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        userRepository.delete(user);
    }
}


