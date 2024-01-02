package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.OrderService;
import com.portfolio.ebookstore.service.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/ebookstore/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final EbookService ebookService;
    public final ShoppingCart shoppingCart;
    private final OrderService orderService;
    private final CustomUserDetailsService userDetailsService;


    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("cartItems", shoppingCart.getCartItems());
        model.addAttribute("totalCost", shoppingCart.getTotalCost() != null ? shoppingCart.getTotalCost().toString() : "0");
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        return "main/cart";
    }



    @PostMapping("/add")
    public String addItem(@RequestParam(name = "ebookId") Long ebookId) {
        EbookDto ebookDto = ebookService.getEbookDtoById(ebookId);
        shoppingCart.addItem(ebookDto);
        return "redirect:/ebookstore";
    }

    //@TODO cart empty after ordering, change cartitem
    @PostMapping
    @RequestMapping("/guest/place-order")
    public String guestOrder(UserDto userDto) {
        orderService.guestOrder(userDto);
        return "redirect:/ebookstore/cart";
    }
//    Principal principal) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", userDetails);
    @PostMapping
    @RequestMapping("/place-order")
    public String loggedOrder(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        orderService.loggedOrder(userDetails);
        return "redirect:/ebookstore/cart";
    }
}
