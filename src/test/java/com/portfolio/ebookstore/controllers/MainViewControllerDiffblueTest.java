package com.portfolio.ebookstore.controllers;

import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.service.EbookService;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {MainViewController.class})
@ExtendWith(SpringExtension.class)
class MainViewControllerDiffblueTest {
    @MockBean
    private EbookService ebookService;

    @Autowired
    private MainViewController mainViewController;

    @MockBean
    private Mappers mappers;

    @MockBean
    private ShoppingCart shoppingCart;

    /**
     * Method under test: {@link MainViewController#ebook(Model)}
     */
    @Test
    void testEbook() throws Exception {
        when(ebookService.getAvailableEbookDtos()).thenReturn(new ArrayList<>());
        when(shoppingCart.getCartSize()).thenReturn(3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore");
        MockMvcBuilders.standaloneSetup(mainViewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cartSize", "ebooks"))
                .andExpect(MockMvcResultMatchers.view().name("main/ebookstore"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("main/ebookstore"));
    }

    /**
     * Method under test: {@link MainViewController#ebook(Model)}
     */
    @Test
    void testEbook2() throws Exception {
        when(ebookService.getAvailableEbookDtos()).thenReturn(new ArrayList<>());
        when(shoppingCart.getCartSize()).thenReturn(3);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(mainViewController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link MainViewController#ebookDetails(Model, Long)}
     */
    @Test
    void testEbookDetails() throws Exception {
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
        when(shoppingCart.getCartSize()).thenReturn(3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/details/{ebookId}", 1L);
        MockMvcBuilders.standaloneSetup(mainViewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cartSize", "ebookById"))
                .andExpect(MockMvcResultMatchers.view().name("main/ebook-details"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("main/ebook-details"));
    }

    /**
     * Method under test: {@link MainViewController#ebookSearch(String, Model)}
     */
    @Test
    void testEbookSearch() throws Exception {
        when(ebookService.searchingForEbook(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(mappers.ebookListEntityToDto(Mockito.<List<Ebook>>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ebookstore/search")
                .param("keyword", "foo");
        MockMvcBuilders.standaloneSetup(mainViewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("keyword", "searchResult"))
                .andExpect(MockMvcResultMatchers.view().name("main/search-results"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("main/search-results"));
    }
}

