package com.portfolio.ebookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String userEmail;
    private BigDecimal totalCost;
    private String orderTime;
    private List<EbookDto> ebooks;
}
