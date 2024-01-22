package com.portfolio.ebookstore.controllers;

import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.service.OrderService;
import com.portfolio.ebookstore.service.user.CustomUserDetail;
import com.portfolio.ebookstore.service.user.UserService;
import com.sun.security.auth.UserPrincipal;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerDiffblueTest {
    @MockBean
    private Mappers mappers;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ShoppingCart shoppingCart;

    @Autowired
    private UserController userController;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getRegistrationView(UserDto, Model)}
     */
    @Test
    void testGetRegistrationView() throws Exception {
        when(shoppingCart.getCartSize()).thenReturn(3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/registration");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cartSize", "user"))
                .andExpect(MockMvcResultMatchers.view().name("/user/register"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/register"));
    }

    /**
     * Method under test: {@link UserController#saveUser(UserDto, Model)}
     */
    @Test
    void testSaveUser() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPastPurchases(new ArrayList<>());
        user.setRole("Role");
        when(userService.saveUser(Mockito.<UserDto>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ebookstore/registration");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message", "user"))
                .andExpect(MockMvcResultMatchers.view().name("/user/register"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/register"));
    }

    /**
     * Method under test: {@link UserController#getRegistrationView(UserDto, Model)}
     */
    @Test
    void testGetRegistrationView2() throws Exception {
        when(shoppingCart.getCartSize()).thenReturn(3);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link UserController#login()}
     */
    @Test
    void testLogin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("/user/login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/login"));
    }

    /**
     * Method under test: {@link UserController#login()}
     */
    @Test
    void testLogin2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("/user/login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/login"));
    }

    /**
     * Method under test: {@link UserController#purchaseDetails(Model, Long)}
     */
    @Test
    void testPurchaseDetails() throws Exception {
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
        when(orderService.getOrderById(Mockito.<Long>any())).thenReturn(order);
        when(orderService.getEbooksFromPastOrders(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        OrderDto.OrderDtoBuilder builderResult = OrderDto.builder();
        OrderDto.OrderDtoBuilder orderTimeResult = builderResult.ebooks(new ArrayList<>()).id(1L).orderTime("Order Time");
        when(mappers.orderEntityToDto(Mockito.<Order>any()))
                .thenReturn(orderTimeResult.totalCost(new BigDecimal("2.3")).userEmail("jane.doe@example.org").build());
        when(shoppingCart.getCartSize()).thenReturn(3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user-page/orders/{orderId}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cartSize", "orderById", "orderedEbooks"))
                .andExpect(MockMvcResultMatchers.view().name("/user/order-details"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/order-details"));
    }

    /**
     * Method under test: {@link UserController#userPage(Model, Principal)}
     */
    @Test
    void testUserPage() throws Exception {
        when(orderService.getOrdersForUser(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(mappers.orderListEntityToDto(Mockito.<List<Order>>any())).thenReturn(new ArrayList<>());
        when(shoppingCart.getCartSize()).thenReturn(3);
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new CustomUserDetail(new User()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user-page");
        requestBuilder.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cartSize", "orders", "user"))
                .andExpect(MockMvcResultMatchers.view().name("/user/user"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/user"));
    }

    /**
     * Method under test: {@link UserController#userPage(Model, Principal)}
     */
    @Test
    void testUserPage2() throws Exception {
        when(orderService.getOrdersForUser(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(mappers.orderListEntityToDto(Mockito.<List<Order>>any())).thenReturn(new ArrayList<>());
        when(shoppingCart.getCartSize()).thenReturn(3);
        when(userDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new org.springframework.security.core.userdetails.User("janedoe", "iloveyou", new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user-page");
        requestBuilder.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cartSize", "orders", "user"))
                .andExpect(MockMvcResultMatchers.view().name("/user/user"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user/user"));
    }
}

