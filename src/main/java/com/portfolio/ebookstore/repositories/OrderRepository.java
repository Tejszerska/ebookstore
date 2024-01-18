package com.portfolio.ebookstore.repositories;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllById(Long id);
    List<Order> findAllByUser(User user);
    List<Order> findAllByEbooksContaining(Ebook ebook);
}

