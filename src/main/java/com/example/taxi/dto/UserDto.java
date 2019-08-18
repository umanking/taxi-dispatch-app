package com.example.taxi.dto;

import com.example.taxi.domain.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    @Setter
    public static class UserRequest {
        private String email;
        private String password;
        private UserStatus userStatus;

    }

    @Getter
    @Setter
    public static class UserResponse {
        private String email;
        private UserStatus userStatus;
    }

}
