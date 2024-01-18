package com.portfolio.ebookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.EbookRepository;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.repositories.UserRepository;
import com.portfolio.ebookstore.service.user.CustomUserDetail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplDiffblueTest {
    @MockBean
    private EbookRepository ebookRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private ShoppingCart shoppingCart;

    @MockBean
    private UserRepository userRepository;

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
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");

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

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        Order order = mock(Order.class);
        when(order.getEbooks()).thenReturn(ebookList);
        doNothing().when(order).setEbooks(Mockito.<List<Ebook>>any());
        doNothing().when(order).setId(Mockito.<Long>any());
        doNothing().when(order).setOrderTime(Mockito.<LocalDateTime>any());
        doNothing().when(order).setTotalCost(Mockito.<BigDecimal>any());
        doNothing().when(order).setUser(Mockito.<User>any());
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Ebook> actualEbooksFromPastOrders = orderServiceImpl.getEbooksFromPastOrders(1L);
        assertSame(ebookList, actualEbooksFromPastOrders);
        assertEquals(1, actualEbooksFromPastOrders.size());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).getEbooks();
        verify(order).setEbooks(Mockito.<List<Ebook>>any());
        verify(order).setId(Mockito.<Long>any());
        verify(order).setOrderTime(Mockito.<LocalDateTime>any());
        verify(order).setTotalCost(Mockito.<BigDecimal>any());
        verify(order).setUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#guestOrder(UserDto)}
     */
    @Test
    void testGuestOrder() {
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
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPastPurchases(new ArrayList<>());
        user2.setRole("Role");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(shoppingCart.getTotalCost()).thenReturn(new BigDecimal("2.3"));
        when(shoppingCart.getCartItems()).thenReturn(new ArrayList<>());
        orderServiceImpl.guestOrder(new UserDto());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(shoppingCart).getTotalCost();
        verify(shoppingCart).getCartItems();
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

        OrderRepository orderRepository = mock(OrderRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        EbookRepository ebookRepository = mock(EbookRepository.class);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository, userRepository, ebookRepository,
                new ShoppingCart());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartItems(new ArrayList<>());
        assertTrue(orderServiceImpl.getEbooksFromCart(shoppingCart).isEmpty());
    }

    /**
     * Method under test: {@link OrderServiceImpl#getEbooksFromCart(ShoppingCart)}
     */
    @Test
    void testGetEbooksFromCart2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.portfolio.ebookstore.components.ShoppingCart
        //   See https://diff.blue/R027 to resolve this issue.

        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("Image Name");
        ebook.setPublisher("Publisher");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");
        Optional<Ebook> ofResult = Optional.of(ebook);
        EbookRepository ebookRepository = mock(EbookRepository.class);
        when(ebookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        OrderRepository orderRepository = mock(OrderRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository, userRepository, ebookRepository,
                new ShoppingCart());

        ShoppingCart shoppingCart = new ShoppingCart();
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        shoppingCart.addItem(purchaseCostResult.sellingPrice(new BigDecimal("2.3")).title("Dr").build());
        assertEquals(1, orderServiceImpl.getEbooksFromCart(shoppingCart).size());
        verify(ebookRepository).findById(Mockito.<Long>any());
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

    /**
     * Method under test: {@link OrderServiceImpl#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(orderServiceImpl.getOrdersForUser("janedoe").isEmpty());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser2() {
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

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        assertTrue(orderServiceImpl.getOrdersForUser("janedoe").isEmpty());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser3() {
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

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2L);
        user2.setName("janedoe");
        user2.setPassword("jane.doe@example.org");
        user2.setPastPurchases(new ArrayList<>());
        user2.setRole("janedoe");

        Order order2 = new Order();
        order2.setEbooks(new ArrayList<>());
        order2.setId(2L);
        order2.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setTotalCost(new BigDecimal("2.3"));
        order2.setUser(user2);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        assertTrue(orderServiceImpl.getOrdersForUser("janedoe").isEmpty());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser4() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPastPurchases(new ArrayList<>());
        user2.setRole("Role");
        Order order = mock(Order.class);
        when(order.getUser()).thenReturn(user2);
        doNothing().when(order).setEbooks(Mockito.<List<Ebook>>any());
        doNothing().when(order).setId(Mockito.<Long>any());
        doNothing().when(order).setOrderTime(Mockito.<LocalDateTime>any());
        doNothing().when(order).setTotalCost(Mockito.<BigDecimal>any());
        doNothing().when(order).setUser(Mockito.<User>any());
        order.setEbooks(new ArrayList<>());
        order.setId(1L);
        order.setOrderTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setTotalCost(new BigDecimal("2.3"));
        order.setUser(user);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        assertTrue(orderServiceImpl.getOrdersForUser("janedoe").isEmpty());
        verify(orderRepository).findAll();
        verify(order).getUser();
        verify(order).setEbooks(Mockito.<List<Ebook>>any());
        verify(order).setId(Mockito.<Long>any());
        verify(order).setOrderTime(Mockito.<LocalDateTime>any());
        verify(order).setTotalCost(Mockito.<BigDecimal>any());
        verify(order).setUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#loggedOrder(UserDetails)}
     */
    @Test
    void testLoggedOrder() {
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
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPastPurchases(new ArrayList<>());
        user2.setRole("Role");
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(user2);
        when(shoppingCart.getTotalCost()).thenReturn(new BigDecimal("2.3"));
        when(shoppingCart.getCartItems()).thenReturn(new ArrayList<>());
        orderServiceImpl.loggedOrder(new CustomUserDetail(new User()));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(shoppingCart).getTotalCost();
        verify(shoppingCart).getCartItems();
    }

    /**
     * Method under test: {@link OrderServiceImpl#searchForOrder(String, String)}
     */
    @Test
    void testSearchForOrder() {
        assertTrue(orderServiceImpl.searchForOrder("Keyword", "Criteria").isEmpty());
    }

    /**
     * Method under test: {@link OrderServiceImpl#searchForOrder(String, String)}
     */
    @Test
    void testSearchForOrder2() {
        when(ebookRepository.findAllByTitleContaining(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertTrue(orderServiceImpl.searchForOrder("Keyword", "ebook").isEmpty());
        verify(ebookRepository).findAllByTitleContaining(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#searchForOrder(String, String)}
     */
    @Test
    void testSearchForOrder3() {
        when(orderRepository.findAllByEbooksContaining(Mockito.<Ebook>any())).thenReturn(new ArrayList<>());

        Ebook ebook = new Ebook();
        ebook.setAuthors("JaneDoe");
        ebook.setAvailable(true);
        ebook.setDescription("The characteristics of someone or something");
        ebook.setGenre(Genre.FICTION);
        ebook.setId(1L);
        ebook.setImageName("ebook");
        ebook.setPublisher("ebook");
        ebook.setPurchaseCost(new BigDecimal("2.3"));
        ebook.setSellingPrice(new BigDecimal("2.3"));
        ebook.setTitle("Dr");

        ArrayList<Ebook> ebookList = new ArrayList<>();
        ebookList.add(ebook);
        when(ebookRepository.findAllByTitleContaining(Mockito.<String>any())).thenReturn(ebookList);
        assertTrue(orderServiceImpl.searchForOrder("Keyword", "ebook").isEmpty());
        verify(orderRepository).findAllByEbooksContaining(Mockito.<Ebook>any());
        verify(ebookRepository).findAllByTitleContaining(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#searchForOrder(String, String)}
     */
    @Test
    void testSearchForOrder4() {
        when(userRepository.findAllByEmailContaining(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertTrue(orderServiceImpl.searchForOrder("Keyword", "userContaining").isEmpty());
        verify(userRepository).findAllByEmailContaining(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#searchForOrder(String, String)}
     */
    @Test
    void testSearchForOrder5() {
        when(orderRepository.findAllByUser(Mockito.<User>any())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("userContaining");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("userContaining");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAllByEmailContaining(Mockito.<String>any())).thenReturn(userList);
        assertTrue(orderServiceImpl.searchForOrder("Keyword", "userContaining").isEmpty());
        verify(orderRepository).findAllByUser(Mockito.<User>any());
        verify(userRepository).findAllByEmailContaining(Mockito.<String>any());
    }
}

