package com.example.taxi.dto;

import com.example.taxi.domain.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

public class LoginDto {

    @Getter
    @Setter
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class LoginResponse {
        private String email;
        private UserStatus userStatus;
    }
}
