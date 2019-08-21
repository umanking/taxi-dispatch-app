package com.example.taxi.domain.user;

import com.example.taxi.common.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.internal.util.Assert;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class User extends BaseJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Builder(builderMethodName = "ByCreateBuilder")
    public User(String email, String password, UserStatus userStatus) {
        Assert.notNull(email, "email must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(userStatus, "userStatus must not be null");
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User() {
    }
}
