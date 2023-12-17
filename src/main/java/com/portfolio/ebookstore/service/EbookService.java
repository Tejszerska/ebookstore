package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EbookService {
    List<EbookDto> getEbookDtos();
     List<EbookDto> getAvailableEbookDtos();
    EbookDto getEbookDtoById(Long ebookId);
    void addEbook(EbookDto ebookDto, MultipartFile file);
    void coverUpload(MultipartFile file);
    Ebook editEbook(EbookDto ebookDto, Long ebookId);
    void editEbookWithoutCover(EbookDto ebookDto, Long ebookId);
    void editEbookWithCover(EbookDto ebookDto, Long ebookId, MultipartFile file);

}
