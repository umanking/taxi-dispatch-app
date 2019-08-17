package com.example.taxi.domain.user;

import com.example.taxi.dto.LoginDto;
import com.example.taxi.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserDto.UserResponse saveUser(UserDto.UserRequest user) {
        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw new RuntimeException("이미 존재하는 유저입니다.");
        }

        User savedUser = userRepository.save(User.builder()
                .email(user.getEmail())
//                .password(passwordEncoder.encode(user.getPassword()))
                .password(user.getPassword())
                .userStatus(user.getUserStatus())
                .build());

        return modelMapper.map(savedUser, UserDto.UserResponse.class);
    }


    public LoginDto.LoginResponse login(HttpServletRequest request, LoginDto.LoginRequest user) {
        // email, password validate
        User existUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authenticate = manager.authenticate(token);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authenticate);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        if (existUser != null) {
            return modelMapper.map(existUser, LoginDto.LoginResponse.class);
        }
        throw new RuntimeException("로그인 실패");

    }

}
