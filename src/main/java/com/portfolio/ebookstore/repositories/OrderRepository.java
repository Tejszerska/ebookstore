package com.portfolio.ebookstore.repositories;

import com.portfolio.ebookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findAllByUser(String keyword);
}

