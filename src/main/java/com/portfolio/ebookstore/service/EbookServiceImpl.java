package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.model.dto.EbookDto;
import com.portfolio.ebookstore.model.enums.Genre;
import com.portfolio.ebookstore.repositories.EbookRepository;
import com.portfolio.ebookstore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EbookServiceImpl implements EbookService {
    private final EbookRepository ebookRepository;
    private final OrderRepository orderRepository;

    public List<EbookDto> getEbookDtos() {
        return ebookRepository.findAll().stream()
                .map(e -> new EbookDto(e.getId(), e.getTitle(), e.getAuthors(), e.getPublisher(), e.getImageName(), e.getDescription(), e.getGenre().toString(), e.getSellingPrice(), e.getPurchaseCost(), e.isAvailable()))
                .toList();
    }

    public List<EbookDto> getAvailableEbookDtos() {
       return getEbookDtos().stream().filter( e -> e.isAvailable()).toList();
    }
//    public EbookDto getAvailableEbookDtoById(Long ebookId){
//        EbookDto ebookDto = getEbookDtoById(ebookId);
//        if(ebookDto.isAvailable()){
//            return ebookDto;
//        }
//    }


    public EbookDto getEbookDtoById(Long ebookId) {
        Ebook ebook = ebookRepository.findById(ebookId).orElseThrow(() -> new IllegalArgumentException(ebookId + ": there is no ebook with that ID in the database."));
        return new EbookDto(ebook.getId(), ebook.getTitle(), ebook.getAuthors(), ebook.getPublisher(), ebook.getImageName(), ebook.getDescription(), ebook.getGenre().toString(), ebook.getSellingPrice(), ebook.getPurchaseCost(), ebook.isAvailable());
    }

    public void addEbook(EbookDto ebookDto, MultipartFile file) {
        Ebook ebook = new Ebook();
            ebook.setPurchaseCost(ebookDto.getPurchaseCost());
            ebook.setTitle(ebookDto.getTitle());
            ebook.setId(ebookDto.getId());
            ebook.setAuthors(ebookDto.getAuthors());
            ebook.setPublisher(ebookDto.getPublisher());
            ebook.setImageName(ebookDto.getImageName());
            ebook.setDescription(ebookDto.getDescription());
            ebook.setGenre(Genre.valueOf(ebookDto.getGenre()));
            ebook.setSellingPrice(ebookDto.getSellingPrice());
            ebook.setAvailable(ebookDto.isAvailable());

        coverUpload(file);
        ebookRepository.save(ebook);
    }

    public void coverUpload(MultipartFile file) {
        Path img = Paths.get("src/main/resources/static/img");

        // Check if the uploads directory exists, create it if not
        if (!Files.exists(img)) {
            try {
                Files.createDirectories(img);
            } catch (IOException e) {
                log.error("Failed to create images directory: " + e.getMessage());
                return;
            }
        }

        try {
            Files.copy(file.getInputStream(), img.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            log.error("Failed to upload cover image: " + e.getMessage());
        }
    }

    public Ebook editEbook(EbookDto ebookDto, Long ebookId) {
        Ebook ebook = ebookRepository.findById(ebookId).orElseThrow(() -> new IllegalArgumentException(ebookId + ": there is no ebook with that ID in the database."));
        ebook.setTitle(ebookDto.getTitle());
        ebook.setAuthors(ebookDto.getAuthors());
        ebook.setPublisher(ebookDto.getPublisher());
        ebook.setDescription(ebookDto.getDescription());
        ebook.setGenre(Genre.valueOf(ebookDto.getGenre()));
        ebook.setSellingPrice(ebookDto.getSellingPrice());
        ebook.setPurchaseCost(ebookDto.getPurchaseCost());
        ebook.setAvailable(ebookDto.isAvailable());
        return ebook;
    }

    public void editEbookWithoutCover(EbookDto ebookDto, Long ebookId) {
        ebookRepository.save(editEbook(ebookDto, ebookId));

    }

    public void editEbookWithCover(EbookDto ebookDto, Long ebookId, MultipartFile file) {
        Ebook ebook = editEbook(ebookDto, ebookId);
        ebook.setImageName(ebookDto.getImageName());
        coverUpload(file);
        ebookRepository.save(ebook);

    }

}
