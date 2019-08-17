package com.example.taxi.domain.user;

import com.example.taxi.common.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class User extends BaseJpaModel {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Builder
    public User(String email, String password, UserStatus userStatus) {
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User() {
    }
}
