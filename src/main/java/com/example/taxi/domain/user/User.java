package com.example.taxi.domain.user;

import com.example.taxi.common.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table
public class User extends BaseJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Builder(builderMethodName = "ByCreateBuilder")
    public User(String email, String password, UserStatus userStatus) {
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User() {
    }
}
