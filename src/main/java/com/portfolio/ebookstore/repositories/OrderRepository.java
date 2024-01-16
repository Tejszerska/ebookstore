package com.portfolio.ebookstore.repositories;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllById(Long id);
    List<Order> findAllByUser(User user);
    List<Order> findAllByOrderTime(LocalDateTime orderTime);


    //@TODO spr jeszcze raz
    @Query(value="SELECT * FROM orders_ebooks WHERE ebooks_id = ?1", nativeQuery = true)
    List<Order> findAllByEbooks(Long ebookId);


}

