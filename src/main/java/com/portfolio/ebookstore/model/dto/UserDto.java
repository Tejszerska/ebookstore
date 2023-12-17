package com.portfolio.ebookstore.model.dto;

import com.portfolio.ebookstore.entities.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDto {
    private String email;
    private String password;
    private String role;
    private String name;
    private List<OrderDto> pastPurchases;

}
