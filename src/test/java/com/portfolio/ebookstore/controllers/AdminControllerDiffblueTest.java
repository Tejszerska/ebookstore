package com.portfolio.ebookstore.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.repositories.EbookRepository;
import com.portfolio.ebookstore.repositories.OrderRepository;
import com.portfolio.ebookstore.repositories.UserRepository;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.EbookServiceImpl;
import com.portfolio.ebookstore.service.OrderService;
import com.portfolio.ebookstore.service.OrderServiceImpl;
import com.portfolio.ebookstore.service.user.CustomUserDetail;
import com.portfolio.ebookstore.service.user.CustomUserDetailsService;
import com.sun.security.auth.UserPrincipal;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerDiffblueTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private EbookService ebookService;

    @MockBean
    private Mappers mappers;

    @MockBean
    private OrderService orderService;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test: {@link AdminController#addEbook(EbookDto, MultipartFile)}
     */
    @Test
    void testAddEbook() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EbookServiceImpl ebookService = mock(EbookServiceImpl.class);
        doNothing().when(ebookService).addEbook(Mockito.<EbookDto>any(), Mockito.<MultipartFile>any());
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(mock(UserRepository.class));
        OrderRepository orderRepository = mock(OrderRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        EbookRepository ebookRepository = mock(EbookRepository.class);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, userRepository, ebookRepository,
                new ShoppingCart());

        AdminController adminController = new AdminController(userDetailsService, orderService, ebookService,
                new Mappers());
        EbookDto ebookDto = new EbookDto();
        assertEquals("redirect:/admin-page/ebooks/add-view", adminController.addEbook(ebookDto,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(ebookService).addEbook(Mockito.<EbookDto>any(), Mockito.<MultipartFile>any());
        assertEquals("", ebookDto.getImageName());
    }

    /**
     * Method under test: {@link AdminController#addEbook(EbookDto, MultipartFile)}
     */
    @Test
    void testAddEbook2() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EbookServiceImpl ebookService = mock(EbookServiceImpl.class);
        doNothing().when(ebookService).addEbook(Mockito.<EbookDto>any(), Mockito.<MultipartFile>any());
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(mock(UserRepository.class));
        OrderRepository orderRepository = mock(OrderRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        EbookRepository ebookRepository = mock(EbookRepository.class);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, userRepository, ebookRepository,
                new ShoppingCart());

        AdminController adminController = new AdminController(userDetailsService, orderService, ebookService,
                new Mappers());
        EbookDto ebookDto = mock(EbookDto.class);
        doNothing().when(ebookDto).setImageName(Mockito.<String>any());
        assertEquals("redirect:/admin-page/ebooks/add-view", adminController.addEbook(ebookDto,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(ebookService).addEbook(Mockito.<EbookDto>any(), Mockito.<MultipartFile>any());
        verify(ebookDto).setImageName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminController#adminPage(Model, Principal)}
     */
    @Test
    void testAdminPage() throws Exception {
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new CustomUserDetail(new User()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page");
        requestBuilder.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/admin"));
    }

    /**
     * Method under test: {@link AdminController#ebookSearch(String, Model)}
     */
    @Test
    void testEbookSearch() throws Exception {
        when(ebookService.searchingForEbook(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(mappers.ebookListEntityToDto(Mockito.<List<Ebook>>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/ebooks/search")
                .param("keyword", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("keyword", "searchResult"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/ebooks-search"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/ebooks-search"));
    }

    /**
     * Method under test: {@link AdminController#editEbook(EbookDto, MultipartFile, Long)}
     */
    @Test
    void testEditEbook() throws Exception {
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin-page/ebooks/edit/{ebookId}", "", "Uri Variables")
                .param("cover", String.valueOf(new MockMultipartFile("Name", contentStream)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AdminController#getAddEbookView(Model)}
     */
    @Test
    void testGetAddEbookView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/ebooks/add-view");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("genres", "newEbook"))
                .andExpect(MockMvcResultMatchers.view().name("admin/add-ebook"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/add-ebook"));
    }

    /**
     * Method under test: {@link AdminController#getAddEbookView(Model)}
     */
    @Test
    void testGetAddEbookView2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/ebooks/add-view");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("genres", "newEbook"))
                .andExpect(MockMvcResultMatchers.view().name("admin/add-ebook"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/add-ebook"));
    }

    /**
     * Method under test: {@link AdminController#getEbooksList(Model)}
     */
    @Test
    void testGetEbooksList() throws Exception {
        when(ebookService.getEbookDtos()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/ebooks");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ebooksDtos"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/ebooks"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/ebooks"));
    }

    /**
     * Method under test: {@link AdminController#getEditEbookView(Model, Long)}
     */
    @Test
    void testGetEditEbookView() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin-page/ebooks/edit-view/{ebookId}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("editedEbook", "genres"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/edit-ebook"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/edit-ebook"));
    }

    /**
     * Method under test: {@link AdminController#getOrdersList(Model)}
     */
    @Test
    void testGetOrdersList() throws Exception {
        when(orderService.getOrders()).thenReturn(new ArrayList<>());
        when(mappers.orderListEntityToDto(Mockito.<List<Order>>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/orders");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("orders"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/orders"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/orders"));
    }

    /**
     * Method under test: {@link AdminController#orderSearch(Model, String, String)}
     */
    @Test
    void testOrderSearch() throws Exception {
        when(orderService.searchForOrder(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(mappers.orderListEntityToDto(Mockito.<List<Order>>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/orders/search")
                .param("criteria", "foo")
                .param("keyword", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("keyword", "searchResult"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/orders-search"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/orders-search"));
    }

    /**
     * Method under test: {@link AdminController#purchaseDetails(Model, Long)}
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin-page/orders/{orderId}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("orderById", "orderedEbooks"))
                .andExpect(MockMvcResultMatchers.view().name("/admin/order-details"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/order-details"));
    }
}

