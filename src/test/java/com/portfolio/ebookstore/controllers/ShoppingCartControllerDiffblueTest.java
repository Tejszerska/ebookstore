package com.portfolio.ebookstore.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.OrderService;
import com.portfolio.ebookstore.service.user.CustomUserDetail;
import com.portfolio.ebookstore.service.user.CustomUserDetailsService;
import com.sun.security.auth.UserPrincipal;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {ShoppingCartController.class})
@ExtendWith(SpringExtension.class)
class ShoppingCartControllerDiffblueTest {
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private EbookService ebookService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ShoppingCart shoppingCart;

    @Autowired
    private ShoppingCartController shoppingCartController;

    /**
     * Method under test: {@link ShoppingCartController#addItem(Long)}
     */
    @Test
    void testAddItem() throws Exception {
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        when(ebookService.getEbookDtoById(Mockito.<Long>any()))
                .thenReturn(purchaseCostResult.sellingPrice(new BigDecimal("2.3")).title("Dr").build());
        doNothing().when(shoppingCart).addItem(Mockito.<EbookDto>any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/ebookstore/cart/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("ebookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore"));
    }

    /**
     * Method under test: {@link ShoppingCartController#addItemInCart(Long)}
     */
    @Test
    void testAddItemInCart() throws Exception {
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        when(ebookService.getEbookDtoById(Mockito.<Long>any()))
                .thenReturn(purchaseCostResult.sellingPrice(new BigDecimal("2.3")).title("Dr").build());
        doNothing().when(shoppingCart).addItem(Mockito.<EbookDto>any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/ebookstore/cart/add-in-cart");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("ebookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#clearCart()}
     */
    @Test
    void testClearCart() throws Exception {
        doNothing().when(shoppingCart).clearCart();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/cart/clear-cart");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#clearCart()}
     */
    @Test
    void testClearCart2() throws Exception {
        doNothing().when(shoppingCart).clearCart();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/cart/clear-cart");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#guestOrder(UserDto)}
     */
    @Test
    void testGuestOrder() throws Exception {
        doNothing().when(shoppingCart).clearCart();
        doNothing().when(orderService).guestOrder(Mockito.<UserDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ebookstore/cart/guest/place-order");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#loggedOrder(Model, Principal)}
     */
    @Test
    void testLoggedOrder() throws Exception {
        doNothing().when(shoppingCart).clearCart();
        doNothing().when(orderService).loggedOrder(Mockito.<UserDetails>any());
        when(customUserDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new CustomUserDetail(new User()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/cart/place-order");
        requestBuilder.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#removeItemInCart(Long)}
     */
    @Test
    void testRemoveItemInCart() throws Exception {
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        when(ebookService.getEbookDtoById(Mockito.<Long>any()))
                .thenReturn(purchaseCostResult.sellingPrice(new BigDecimal("2.3")).title("Dr").build());
        doNothing().when(shoppingCart).removeItem(Mockito.<EbookDto>any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/ebookstore/cart/remove-in-cart");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("ebookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#showCart(Model)}
     */
    @Test
    void testShowCart() throws Exception {
        when(shoppingCart.getCartSize()).thenReturn(3);
        when(shoppingCart.getTotalCost()).thenReturn(new BigDecimal("2.3"));
        when(shoppingCart.getCartItems()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/cart");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(5))
                .andExpect(
                        MockMvcResultMatchers.model().attributeExists("cartItems", "cartSize", "ordered", "totalCost", "userDto"))
                .andExpect(MockMvcResultMatchers.view().name("main/cart"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("main/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#showCart(Model)}
     */
    @Test
    void testShowCart2() throws Exception {
        when(shoppingCart.getCartSize()).thenReturn(3);
        when(shoppingCart.getTotalCost()).thenReturn(null);
        when(shoppingCart.getCartItems()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/cart");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(5))
                .andExpect(
                        MockMvcResultMatchers.model().attributeExists("cartItems", "cartSize", "ordered", "totalCost", "userDto"))
                .andExpect(MockMvcResultMatchers.view().name("main/cart"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("main/cart"));
    }

    /**
     * Method under test: {@link ShoppingCartController#subtractItemInCart(Long)}
     */
    @Test
    void testSubtractItemInCart() throws Exception {
        EbookDto.EbookDtoBuilder publisherResult = EbookDto.builder()
                .authors("JaneDoe")
                .description("The characteristics of someone or something")
                .genre("Genre")
                .id(1L)
                .imageName("Image Name")
                .publisher("Publisher");
        EbookDto.EbookDtoBuilder purchaseCostResult = publisherResult.purchaseCost(new BigDecimal("2.3"));
        when(ebookService.getEbookDtoById(Mockito.<Long>any()))
                .thenReturn(purchaseCostResult.sellingPrice(new BigDecimal("2.3")).title("Dr").build());
        doNothing().when(shoppingCart).subtractItem(Mockito.<EbookDto>any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/ebookstore/cart/subtract-in-cart");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("ebookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ebookstore/cart"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ebookstore/cart"));
    }
}

