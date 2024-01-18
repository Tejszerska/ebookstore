package com.portfolio.ebookstore.controllers;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.components.ShoppingCart;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.service.EbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ebookstore")
public class MainViewController {
    private final EbookService ebookService;
    private final ShoppingCart shoppingCart;
    private final Mappers mappers;
    @GetMapping
    public String ebook(Model model) {
        List<EbookDto> availableEbookDtosFromDb = ebookService.getAvailableEbookDtos();
        model.addAttribute("ebooks", availableEbookDtosFromDb);
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        return "main/ebookstore";
    }
    @RequestMapping("/details/{ebookId}")
    public String ebookDetails(Model model, @PathVariable Long ebookId) throws IllegalArgumentException {
        EbookDto ebookById = ebookService.getEbookDtoById(ebookId);
        model.addAttribute("ebookById", ebookById);
        int cartSize = shoppingCart.getCartSize();
        model.addAttribute("cartSize", cartSize);
        return "main/ebook-details";
    }
    @GetMapping("/search")
    public String ebookSearch(@RequestParam("keyword") String keyword, Model model) {
        List<EbookDto> searchResult = mappers.ebookListEntityToDto(ebookService.searchingForEbook(keyword));
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("keyword", keyword);
        return "main/search-results";
    }
}
