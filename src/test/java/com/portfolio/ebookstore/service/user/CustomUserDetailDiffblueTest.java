package com.portfolio.ebookstore.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.portfolio.ebookstore.entities.User;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CustomUserDetailDiffblueTest {
    /**
     * Method under test: {@link CustomUserDetail#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        assertEquals(1, (new CustomUserDetail(new User())).getAuthorities().size());
    }

    /**
     * Method under test: {@link CustomUserDetail#getName()}
     */
    @Test
    void testGetName() {
        assertNull((new CustomUserDetail(new User())).getName());
    }

    /**
     * Method under test: {@link CustomUserDetail#getPassword()}
     */
    @Test
    void testGetPassword() {
        assertNull((new CustomUserDetail(new User())).getPassword());
    }

    /**
     * Method under test: {@link CustomUserDetail#getUsername()}
     */
    @Test
    void testGetUsername() {
        assertNull((new CustomUserDetail(new User())).getUsername());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CustomUserDetail#isAccountNonExpired()}
     *   <li>{@link CustomUserDetail#isAccountNonLocked()}
     *   <li>{@link CustomUserDetail#isCredentialsNonExpired()}
     *   <li>{@link CustomUserDetail#isEnabled()}
     * </ul>
     */
    @Test
    void testIsAccountNonExpired() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        boolean actualIsAccountNonExpiredResult = customUserDetail.isAccountNonExpired();
        boolean actualIsAccountNonLockedResult = customUserDetail.isAccountNonLocked();
        boolean actualIsCredentialsNonExpiredResult = customUserDetail.isCredentialsNonExpired();
        assertTrue(actualIsAccountNonExpiredResult);
        assertTrue(actualIsAccountNonLockedResult);
        assertTrue(actualIsCredentialsNonExpiredResult);
        assertTrue(customUserDetail.isEnabled());
    }
}

