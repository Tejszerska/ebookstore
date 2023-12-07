package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.dto.UserDto;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public User save(UserDto userDto) {
         User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .name(userDto.getName()).build();
        return userRepository.save(user);
    }
}
