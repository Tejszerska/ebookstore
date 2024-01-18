package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.service.OrderService;
import com.portfolio.ebookstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final UserDetailsService userDetailsService;
    private final Mappers mappers;
    private final ShoppingCart shoppingCart;
    @GetMapping("ebookstore/registration")
    public String getRegistrationView(@ModelAttribute("user") UserDto userDto, Model model) {
        // CART SIZE
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        return "/user/register";
    }
    @PostMapping("ebookstore/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        userService.saveUser(userDto);
        model.addAttribute("message", "Registered successfully!");
        return "/user/register";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/user-page")
    public String userPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        // LIST OF ORDERS
        List<OrderDto> orders = mappers.orderListEntityToDto(orderService.getOrdersForUser(userDetails.getUsername()));
        model.addAttribute("orders", orders);
        // CART SIZE
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        return "/user/user";
    }
    @GetMapping
    @RequestMapping("/user-page/orders/{orderId}")
    public String purchaseDetails(Model model, @PathVariable Long orderId) {
        OrderDto orderById = mappers.orderEntityToDto(orderService.getOrderById(orderId));
        model.addAttribute("orderById", orderById);
        List<Ebook> orderedEbooks = orderService.getEbooksFromPastOrders(orderId);
        model.addAttribute("orderedEbooks", orderedEbooks);
        // CART SIZE
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        return "/user/order-details";
    }
}
