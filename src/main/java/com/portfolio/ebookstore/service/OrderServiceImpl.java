package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        List<Ebook> ebooks = orderRepository.findById(id).map(Order::getEbooks).get();
        if(!ebooks.isEmpty()) throw new NullPointerException("Ebooks not found.");
        return ebooks;


    }

    @Override
    public void placeOrder() {

    }

    @Override
    public List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> byId = orderRepository.findById(orderId);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new NullPointerException("Order by id: " + orderId + " not found." );
        }
    }
}
