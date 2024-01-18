package com.portfolio.ebookstore.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String email;
    private String password;
    private String role;
    private String name;
    @OneToMany (mappedBy = "user")
    private List<Order> pastPurchases;
}
