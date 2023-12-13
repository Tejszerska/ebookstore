package com.portfolio.ebookstore.service.user;

import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.entities.User;


public interface UserService {
    User save(UserDto userDto);
}
