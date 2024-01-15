package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.service.EbookService;
import com.portfolio.ebookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin-page")
public class AdminController {
    private final UserDetailsService userDetailsService;
    private final OrderService orderService;
    private final EbookService ebookService;
    private final Mappers mappers;

    @GetMapping
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "/admin/admin";
    }


    //Viewing list of ebooks with edit and delete
    @GetMapping
    @RequestMapping("/ebooks")
    public String getEbooksList(Model model) {
        List<EbookDto> ebooksDtos = ebookService.getEbookDtos();
        model.addAttribute("ebooksDtos", ebooksDtos);
        return "/admin/ebooks";
    }

    @PostMapping
    @RequestMapping("/ebooks/edit/{ebookId}")
    public String editEbook(EbookDto ebookDto, @RequestParam("cover") MultipartFile file, @PathVariable Long ebookId) {

        if (file.isEmpty()) {
            ebookService.editEbookWithoutCover(ebookDto, ebookId);
        } else {
            ebookDto.setImageName(file.getOriginalFilename());
            ebookService.editEbookWithCover(ebookDto, ebookId, file);
        }
        return "redirect:/admin-page/ebooks";
    }

    @GetMapping
    @RequestMapping("/ebooks/edit-view/{ebookId}")
    public String getEditEbookView(Model model, @PathVariable Long ebookId) {
        EbookDto editedEbook = ebookService.getEbookDtoById(ebookId);
        model.addAttribute("editedEbook", editedEbook);
        List<String> genreList = Arrays.stream(Genre.values()).map(Enum::name).toList();
        model.addAttribute("genres", genreList);

        return "/admin/edit-ebook";
    }

    // Adding ebook
    @PostMapping
    @RequestMapping("/ebooks/add")
    public String addEbook(EbookDto ebookDto, @RequestParam("image") MultipartFile file) {
        ebookDto.setImageName(file.getOriginalFilename());
        ebookService.addEbook(ebookDto, file);
        return "redirect:/admin-page/ebooks/add-view";
    }

    @GetMapping
    @RequestMapping("/ebooks/add-view")
    public String getAddEbookView(Model model) {
        model.addAttribute("newEbook", new EbookDto());
        List<String> genreList = Arrays.stream(Genre.values()).map(Enum::name).toList();
        model.addAttribute("genres", genreList);
        return "admin/add-ebook";
    }

    //Viewing orders
    @GetMapping
    @RequestMapping("/orders/{orderId}")
    public String purchaseDetails(Model model, @PathVariable Long orderId) {
        OrderDto orderById = mappers.orderEntityToDto(orderService.getOrderById(orderId));
        model.addAttribute("orderById", orderById);
        List<Ebook> orderedEbooks = orderService.getEbooksFromPastOrders(orderId);
        model.addAttribute("orderedEbooks", orderedEbooks);
        return "/admin/order-details";
    }

    @GetMapping
    @RequestMapping("/orders")
    public String getOrdersList(Model model) {
        List<OrderDto> orders = mappers.orderListEntityToDto(orderService.getOrders());
        model.addAttribute("orders", orders);
        return "/admin/orders";
    }


    @GetMapping("/ebooks/search")
    public String ebookSearch(@RequestParam("keyword") String keyword, Model model) {
        List<EbookDto> searchResult = mappers.ebookListEntityToDto(ebookService.searchingForEbook(keyword));
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("keyword", keyword);
        return "/admin/ebooks-search";
    }
}
