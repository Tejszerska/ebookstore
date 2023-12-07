package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.dto.UserDto;
import com.portfolio.ebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
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
}
