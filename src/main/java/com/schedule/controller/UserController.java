package com.schedule.controller;

import jakarta.servlet.http.HttpSession;
import com.schedule.dto.*;
import com.schedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    // 유저 생성
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //전체 조회
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers(){
        List<GetUserResponse> responses = userService.getAllUsers();
        return ResponseEntity.ok(responses);
    }



    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Long id){
        GetUserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request){
        UpdateUserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpSession session) {
        LoginResponse response = userService.login(request);
        session.setAttribute("userId", response.getId());
        session.setAttribute("username", response.getUsername());
        return ResponseEntity.ok(response);
    }

}
