package com.example.taxi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface UserRepository extends JpaRepository<User, Id> {

    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
