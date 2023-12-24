package com.portfolio.ebookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplDiffblueTest {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    /**
     * Method under test: {@link OrderServiceImpl#getOrders()}
     */
    @Test
    void testGetOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> actualOrders = orderServiceImpl.getOrders();
        assertSame(orderList, actualOrders);
        assertTrue(actualOrders.isEmpty());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderServiceImpl#getEbooksFromPastOrders(Long)}
     */
    @Test
    void testGetEbooksFromPastOrders() {
        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Ebooks not found.");
        ebook.setPublisher("Ebooks not found.");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebooks = new ArrayList<>();
        ebooks.add(ebook);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");

        Order order = new Order();
        order.setEbooks(ebooks);
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Ebook> actualEbooksFromPastOrders = orderServiceImpl.getEbooksFromPastOrders(1L);
        assertSame(ebooks, actualEbooksFromPastOrders);
        assertEquals(1, actualEbooksFromPastOrders.size());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder()}
     */
    @Test
    void testPlaceOrder() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     OrderServiceImpl.orderRepository

        (new OrderServiceImpl(mock(OrderRepository.class))).placeOrder();
    }

    /**
     * Method under test: {@link OrderServiceImpl#getEbooksFromCart(ShoppingCart)}
     */
    @Test
    void testGetEbooksFromCart() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(mock(OrderRepository.class));
        assertNull(orderServiceImpl.getEbooksFromCart(new ShoppingCart()));
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrderById(Long)}
     */
    @Test
    void testGetOrderById() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");

        Order order = new Order();
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order, orderServiceImpl.getOrderById(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }
}

