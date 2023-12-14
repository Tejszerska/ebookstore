package com.portfolio.ebookstore.service.user;

import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(UserDto userDto) {
         User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role("USER")
                .name(userDto.getName()).build();
        return userRepository.save(user);
    }

    @Override
    public User saveAdmin(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role("ADMIN")
                .name(userDto.getName()).build();
        return userRepository.save(user);
    }
}
