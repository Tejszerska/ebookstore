package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.dto.UserDto;
import com.portfolio.ebookstore.entities.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User save(UserDto userDto);
}
