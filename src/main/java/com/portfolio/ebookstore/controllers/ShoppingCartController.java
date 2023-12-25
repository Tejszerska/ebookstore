package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ebookstore/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final EbookService ebookService;
    public final ShoppingCart shoppingCart;
    private final OrderService orderService;

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
        orderService.placeOrder(userDto);
        return "redirect:/ebookstore/cart";
    }
}
