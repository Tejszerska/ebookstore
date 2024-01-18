package com.portfolio.ebookstore.service.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.repositories.UserRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplDiffblueTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#saveUser(UserDto)}
     */
    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        UserDto.UserDtoBuilder passwordResult = UserDto.builder()
                .email("jane.doe@example.org")
                .name("Name")
                .password("iloveyou");
        assertSame(user, userServiceImpl.saveUser(passwordResult.pastPurchases(new ArrayList<>()).role("Role").build()));
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveAdmin(UserDto)}
     */
    @Test
    void testSaveAdmin() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        UserDto.UserDtoBuilder passwordResult = UserDto.builder()
                .email("jane.doe@example.org")
                .name("Name")
                .password("iloveyou");
        assertSame(user, userServiceImpl.saveAdmin(passwordResult.pastPurchases(new ArrayList<>()).role("Role").build()));
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }
}

