package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.enums.Genre;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ebooks")
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String authors;
    private String publisher;
    private String imageName;
    private String description;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @NonNull
    private BigDecimal sellingPrice;
    @NonNull
    private BigDecimal purchaseCost;
    @NonNull
    private boolean isAvailable;


}
