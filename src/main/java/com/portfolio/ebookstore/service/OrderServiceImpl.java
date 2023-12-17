package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Ebook> getEbooksFromPastOrders(Long id) {
        return null;
    }

    @Override
    public void placeOrder() {

    }

    @Override
    public List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return null;
    }
}
