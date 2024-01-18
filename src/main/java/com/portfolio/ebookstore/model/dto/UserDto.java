package com.portfolio.ebookstore.model.dto;

import com.portfolio.ebookstore.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String role;
    private String name;
    private List<OrderDto> pastPurchases;
}
