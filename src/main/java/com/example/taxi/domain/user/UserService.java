package com.example.taxi.domain.user;

import com.example.taxi.dto.LoginDto;
import com.example.taxi.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 회원가입 로직
     *
     * @param user
     * @return
     */
    @Transactional
    public UserDto.UserResponse saveUser(UserDto.UserRequest user) {
        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw new RuntimeException("이미 존재하는 유저입니다.");
        }

        User savedUser = userRepository.save(User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .userStatus(user.getUserStatus())
                .build());

        return modelMapper.map(savedUser, UserDto.UserResponse.class);
    }

    /**
     * 로그인 프로세스
     *
     * @param request
     * @param user
     * @return
     */
    public LoginDto.LoginResponse login(HttpServletRequest request, LoginDto.LoginRequest user) {
        // email, password validate
        User existUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (existUser != null) {
            return modelMapper.map(existUser, LoginDto.LoginResponse.class);
        }
        throw new RuntimeException("로그인 실패");

    }

}
