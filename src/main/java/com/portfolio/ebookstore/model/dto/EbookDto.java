package com.portfolio.ebookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EbookDto {
    private Long id;
    private String title;
    private String authors;
    private String publisher;
    private String imageName;
    private String description;
    private String genre;
    private BigDecimal sellingPrice;
    private BigDecimal purchaseCost;
    private boolean isAvailable;
}
