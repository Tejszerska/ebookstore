package com.portfolio.ebookstore.repositories;

import com.portfolio.ebookstore.entities.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

    List<Ebook> findAllByTitleContaining(String keyword);
    List<Ebook> findAllByAuthorsContaining(String keyword);
    List<Ebook> findAllByPublisherContaining(String keyword);
    List<Ebook> findAllByGenreContaining(String keyword);
}
