package com.schedule.dto;

import com.schedule.entity.User;
import lombok.Getter;

@Getter
public class LoginResponse {
    private Long id;
    private String username;
    private String email;

    public LoginResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
