package com.portfolio.ebookstore.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private String email;
    private String password;
    private String role;
    private String name;
}
