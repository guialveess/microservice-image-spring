package com.guialves.image_service.service;

import com.guialves.image_service.dtos.ImageResponseDTO;
import com.guialves.image_service.dtos.ImageRequestDTO;
import com.guialves.image_service.entities.Image;
import com.guialves.image_service.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.guialves.image_service.exceptions.ImageNotFoundException;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Page<ImageResponseDTO> getAllImages(Pageable pageable) {
        return imageRepository.findAll(pageable).map(ImageResponseDTO::new);
    }

    public ImageResponseDTO getImageById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
        return new ImageResponseDTO(image);
    }

    public ImageResponseDTO saveImage(ImageRequestDTO dto) {
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setFileName(dto.getFileName());
        image.setSize(dto.getSize());
        Image saved = imageRepository.save(image);
        return new ImageResponseDTO(saved);
    }

    public ImageResponseDTO deleteImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
        imageRepository.delete(image);
        return new ImageResponseDTO(image);
    }

    public ImageResponseDTO patchImage(Long id, ImageRequestDTO dto) {
        Image existing = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
        if (dto.getFileName() != null) existing.setFileName(dto.getFileName());
        if (dto.getUrl() != null) existing.setUrl(dto.getUrl());
        if (dto.getSize() != null) existing.setSize(dto.getSize());

        Image updated = imageRepository.save(existing);
        return new ImageResponseDTO(updated);
    }
}
