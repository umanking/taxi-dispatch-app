package com.example.taxi.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    @Builder
    public User(String email, String password, UserStatus userStatus){
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User() {
    }
}
