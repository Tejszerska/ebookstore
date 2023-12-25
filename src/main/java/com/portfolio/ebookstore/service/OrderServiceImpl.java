package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCart shoppingCart;
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Ebook> getEbooksFromPastOrders(Long id) {

        List<Ebook> ebooks = orderRepository.findById(id).map(Order::getEbooks).get();
        if(ebooks.isEmpty()) throw new NullPointerException("Ebooks not found.");
        return ebooks;


    }

    @Override
    public void placeOrder(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
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
