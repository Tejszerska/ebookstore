package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin-page")
public class AdminController {
    private final UserDetailsService userDetailsService;
private final OrderService orderService;
private final Mappers mappers;

    @GetMapping
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "/admin/admin";
    }


    //Viewing orders
    @GetMapping
    @RequestMapping("/orders/{orderId}")
    public String purchaseDetails(Model model, @PathVariable Long orderId) {
        OrderDto orderById = mappers.orderEntityToDto(orderService.getOrderById(orderId));
        model.addAttribute("orderById", orderById);
        List<Ebook> orderedEbooks = orderService.getEbooksFromPastOrders(orderById.getId());
        model.addAttribute("orderedEbooks", orderedEbooks);
        return "/admin/order-details";
    }

    @GetMapping
    @RequestMapping("/orders")
    public String getOrdersList(Model model) {
        List<OrderDto> orders = mappers.orderListEntityToDto(orderService.getOrders());
        model.addAttribute("orders", orders);
        return "/admin/orders";
    }

}
