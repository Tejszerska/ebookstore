package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    List<Ebook> getEbooksFromPastOrders(Long id);
    void guestOrder(UserDto userDto);
    List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart);
    Order getOrderById(Long orderId);
    List<Order> getOrdersForUser(String username);
    void loggedOrder(UserDetails userDto);
    List<Order> searchForOrder(String keyword, String criteria);
}
