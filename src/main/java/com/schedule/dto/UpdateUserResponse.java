package com.schedule.dto;

import com.schedule.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateUserResponse {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

 public UpdateUserResponse(User user){
 this.id = user.getId();
 this.username = user.getUsername();
 this.email = user.getEmail();
 this.createdAt = user.getCreatedAt();
 this.modifiedAt = user.getModifiedAt();
 }
}