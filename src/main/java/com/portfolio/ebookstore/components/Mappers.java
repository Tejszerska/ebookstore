package com.portfolio.ebookstore.components;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.dto.OrderDto;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.model.enums.Genre;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappers {

    public UserDto userEntityToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .name(user.getName())
                .pastPurchases(user.getPastPurchases().stream().map(this::orderEntityToDto).collect(Collectors.toList()))
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users) {
        return users.stream()
                .map(this::userEntityToDto)
                .toList();
    }


    public User userDtoToEntity(UserDto userDto, String password) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .name(userDto.getName())
                .pastPurchases(userDto.getPastPurchases().stream().map(this::mapDtoToOrderWithoutUser).collect(Collectors.toList()))
                .build();
    }

    public List<User> userListDtoToEntity(List<UserDto> users, String password) {
        return users.stream()
                .map(user -> userDtoToEntity(user, password))
                .toList();
    }


    public OrderDto orderEntityToDto(Order order) {
        return OrderDto.builder().id(order
                        .getId())
                .userEmail(order.getUser().getEmail())
                .totalCost(order.getTotalCost())
                .orderTime(order.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .ebooks(order.getEbooks().stream().map(this::mapEbookToDto).collect(Collectors.toList()))
                .build();
    }

    public List<OrderDto> orderListEntityToDto(List<Order> orders){
        return orders.stream().map(this::orderEntityToDto).toList();
    }


    public Order mapDtoToOrderWithoutUser(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .totalCost(orderDto.getTotalCost())
                .orderTime(LocalDateTime.parse(orderDto.getOrderTime()))
                .ebooks(orderDto.getEbooks().stream().map(this::mapDtoToEbook).collect(Collectors.toList()))
                .build();
    }

    public EbookDto mapEbookToDto(Ebook ebook) {
        return EbookDto.builder()
                .id(ebook.getId())
                .title(ebook.getTitle())
                .authors(ebook.getAuthors())
                .publisher(ebook.getPublisher())
                .imageName(ebook.getImageName())
                .description(ebook.getDescription())
                .genre(ebook.getGenre().toString())
                .sellingPrice(ebook.getSellingPrice())
                .purchaseCost(ebook.getPurchaseCost())
                .isAvailable(ebook.isAvailable())
                .build();
    }

    public Ebook mapDtoToEbook(EbookDto ebookDto) {
        return Ebook.builder()
                .id(ebookDto.getId())
                .title(ebookDto.getTitle())
                .authors(ebookDto.getAuthors())
                .publisher(ebookDto.getPublisher())
                .imageName(ebookDto.getImageName())
                .description(ebookDto.getDescription())
                .genre(Genre.valueOf(ebookDto.getGenre()))
                .sellingPrice(ebookDto.getSellingPrice())
                .purchaseCost(ebookDto.getPurchaseCost())
                .isAvailable(ebookDto.isAvailable())
                .build();
    }

}

