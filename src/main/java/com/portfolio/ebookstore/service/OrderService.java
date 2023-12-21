package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    List<Ebook> getEbooksFromPastOrders(Long id);
    void placeOrder();
    List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart);
    Order getOrderById(Long orderId);    }
