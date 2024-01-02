package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getOrders();
    List<Ebook> getEbooksFromPastOrders(Long id);
    void placeOrder(UserDto userDto);
    List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart);
    Order getOrderById(Long orderId);
    List<Order> getOrdersForUser(String username);
    List<String> extractTitlesFromOrder(List<OrderDto> orders);

}
