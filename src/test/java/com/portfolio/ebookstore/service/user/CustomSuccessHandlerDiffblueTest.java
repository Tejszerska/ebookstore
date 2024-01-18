package com.portfolio.ebookstore.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomSuccessHandler.class})
@ExtendWith(SpringExtension.class)
class CustomSuccessHandlerDiffblueTest {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    /**
     * Method under test: {@link CustomSuccessHandler#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testOnAuthenticationSuccess() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        customSuccessHandler.onAuthenticationSuccess(request, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        assertTrue(response.isCommitted());
        assertEquals(302, response.getStatus());
        assertEquals("/error", response.getRedirectedUrl());
        assertEquals(1, response.getHeaderNames().size());
    }
}

