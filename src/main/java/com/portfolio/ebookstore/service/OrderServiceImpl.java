package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.repositories.EbookRepository;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EbookRepository ebookRepository;
    private final ShoppingCart shoppingCart;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Ebook> getEbooksFromPastOrders(Long id) {
        List<Ebook> ebooks = orderRepository.findById(id).map(Order::getEbooks).get();
        if (ebooks.isEmpty()) throw new NullPointerException("Ebooks not found.");
        return ebooks;
    }

    @Override
    public void guestOrder(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail() + LocalDateTime.now());
        user.setPassword(null);
        user.setRole("GUEST");
        user.setName(user.getName());
        user.setPastPurchases(null);
        userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setTotalCost(shoppingCart.getTotalCost());
        order.setOrderTime(LocalDateTime.now());
        order.setEbooks(getEbooksFromCart(shoppingCart));
        orderRepository.save(order);
    }

    @Override
    public List<Ebook> getEbooksFromCart(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream().map(c -> ebookRepository.findById(c.getEbookDto().getId()).get()).collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> byId = orderRepository.findById(orderId);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new NullPointerException("Order by id: " + orderId + " not found.");
        }
    }

    @Override
    public List<Order> getOrdersForUser(String username) {
        return getOrders().stream().filter(o -> o.getUser().getEmail().equals(username)).toList();
    }

    @Override
    public void loggedOrder(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByEmail(username);

        Order order = Order.builder()
                .user(user)
                .orderTime(LocalDateTime.now())
                .totalCost(shoppingCart.getTotalCost())
                .ebooks(getEbooksFromCart(shoppingCart))
                .build();

        orderRepository.save(order);

    }

    @Override
    public List<Order> searchForOrder(String keyword, String criteria) {
        switch (criteria) {
            case "id" -> {
                return orderRepository.findAllById(Long.valueOf(keyword));
            }
            case "userFull" -> {
                User user = userRepository.findByEmail(keyword);
                return orderRepository.findAllByUser(user);
            }
            case "userContaining" -> {
                List<User> allByEmailContaining = userRepository.findAllByEmailContaining(keyword);
                List<Order> ordersByUserContaining = new ArrayList<>();
                for (User u : allByEmailContaining) {
                    ordersByUserContaining.addAll(orderRepository.findAllByUser(u));
                }
                return ordersByUserContaining;
            }
            case "ebook" -> {
                List<Ebook> allByTitleContaining = ebookRepository.findAllByTitleContaining(keyword);
                List<Order> allOrdersByEbookTitle = new ArrayList<>();
                for (Ebook e : allByTitleContaining) {
                    allOrdersByEbookTitle.addAll(orderRepository.findAllByEbooksContaining(e));
                }
                return allOrdersByEbookTitle;
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

}