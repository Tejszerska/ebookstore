package com.portfolio.ebookstore.components;

import com.portfolio.ebookstore.model.dto.CartItemDto;
import com.portfolio.ebookstore.model.dto.EbookDto;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
    private List<CartItemDto> cartItems;
    private BigDecimal totalCost;

    public int getCartSize() {
        if (CollectionUtils.isEmpty(cartItems)) {
            return 0;
        } else {
            int total = 0;
            for (CartItemDto cartItemDto : cartItems) {
                total = total + cartItemDto.getQuantity();
            }
            return total;
        }
    }

    public void clearCart() {
        cartItems = null;
        totalCost = BigDecimal.valueOf(0);
    }

    public void addItem(EbookDto ebookDto) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        BigDecimal newEbookPrice = ebookDto.getSellingPrice();
        Optional<CartItemDto> ebookInTheCart = cartItems.stream().filter(c -> c.getEbookDto().getId().equals(ebookDto.getId())).findFirst();
        if (ebookInTheCart.isPresent()) {
            ebookInTheCart.get().setQuantity(ebookInTheCart.get().getQuantity() + 1);
            ebookInTheCart.get().setTotalCost(ebookInTheCart.get().getTotalCost().add(newEbookPrice));
        } else {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setEbookDto(ebookDto);
            cartItemDto.setQuantity(1);
            cartItemDto.setTotalCost(ebookDto.getSellingPrice());
            cartItems.add(cartItemDto);
        }
        if (totalCost == null) {
            totalCost = new BigDecimal(0);
        }
        totalCost = totalCost.add(newEbookPrice);
    }

    public void subtractItem(EbookDto ebookDto) {
        Optional<CartItemDto> ebookInTheCart = cartItems.stream().filter(c -> c.getEbookDto().getId().equals(ebookDto.getId())).findFirst();
        if (ebookInTheCart.isPresent()) {
            if (ebookInTheCart.get().getQuantity() > 1) {
                ebookInTheCart.get().setQuantity(ebookInTheCart.get().getQuantity() - 1);
                ebookInTheCart.get().setTotalCost(ebookInTheCart.get().getTotalCost().subtract(ebookDto.getSellingPrice()));
                totalCost = totalCost.subtract(ebookDto.getSellingPrice());
            } else removeItem(ebookDto);
        }
    }

    public void removeItem(EbookDto ebookDto) {
        Optional<CartItemDto> ebookInTheCart = cartItems.stream().filter(c -> c.getEbookDto().getId().equals(ebookDto.getId())).findFirst();
        if (ebookInTheCart.isPresent()) {
            cartItems.remove(ebookInTheCart.get());
            totalCost = totalCost.subtract(ebookInTheCart.get().getTotalCost());
        }
    }
}
