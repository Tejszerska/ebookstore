package com.portfolio.ebookstore.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.repositories.UserRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class CustomUserDetailsServiceDiffblueTest {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(user);
        assertEquals("Name", ((CustomUserDetail) customUserDetailsService.loadUserByUsername("janedoe")).getName());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(userRepository.findByEmail(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("User not found."));
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
}

