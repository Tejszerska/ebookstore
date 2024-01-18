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
   private boolean ordered = false;
    @PostMapping("/add")
    public String addItem(@RequestParam(name = "ebookId") Long ebookId) {
        ordered = false;
        EbookDto ebookDto = ebookService.getEbookDtoById(ebookId);
        shoppingCart.addItem(ebookDto);
        return "redirect:/ebookstore";
    }
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("cartItems", shoppingCart.getCartItems());
        model.addAttribute("totalCost", shoppingCart.getTotalCost() != null ? shoppingCart.getTotalCost().toString() : "0");
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("ordered", ordered);
        return "main/cart";
    }
    @PostMapping("/remove-in-cart")
    public String removeItemInCart(@RequestParam(name = "ebookId") Long ebookId) {
        EbookDto ebookDto = ebookService.getEbookDtoById(ebookId);
        shoppingCart.removeItem(ebookDto);
        return "redirect:/ebookstore/cart";
    }
    @PostMapping("/subtract-in-cart")
    public String subtractItemInCart(@RequestParam(name = "ebookId") Long ebookId) {
        EbookDto ebookDto = ebookService.getEbookDtoById(ebookId);
        shoppingCart.subtractItem(ebookDto);
        return "redirect:/ebookstore/cart";
    }
    @PostMapping("/add-in-cart")
    public String addItemInCart(@RequestParam(name = "ebookId") Long ebookId) {
        EbookDto ebookDto = ebookService.getEbookDtoById(ebookId);
        shoppingCart.addItem(ebookDto);
        return "redirect:/ebookstore/cart";
    }
    @PostMapping
    @RequestMapping("/clear-cart")
    public String clearCart() {
        shoppingCart.clearCart();
        return "redirect:/ebookstore/cart";
    }
    @PostMapping("/guest/place-order")
    public String guestOrder(UserDto userDto) {
        orderService.guestOrder(userDto);
        shoppingCart.clearCart();
        ordered = true;
        return "redirect:/ebookstore/cart";
    }
    @PostMapping
    @RequestMapping("/place-order")
    public String loggedOrder(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        orderService.loggedOrder(userDetails);
        shoppingCart.clearCart();
        ordered = true;
        return "redirect:/ebookstore/cart";
    }
}
