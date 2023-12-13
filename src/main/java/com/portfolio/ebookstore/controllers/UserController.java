package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.dto.UserDto;
import com.portfolio.ebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    @GetMapping("/registration")
    public String getRegistrationView(@ModelAttribute ("user") UserDto userDto){
        return "/user/register";
    }
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute ("user") UserDto userDto, Model model){
        userService.save(userDto);
        model.addAttribute("message", "Registered successfully!");
        return "/user/register";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("user-page")
    public String userPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "/user/user";
    }

    @GetMapping("admin-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "/user/admin";
    }
}
