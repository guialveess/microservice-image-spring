package com.guialves.image_service.core.service;

import com.guialves.image_service.application.dtos.ImageResponseDTO;
import com.guialves.image_service.application.dtos.ImageRequestDTO;
import com.guialves.image_service.core.entities.Image;
import com.guialves.image_service.infrastructure.repository.ImageRepository;
import com.guialves.image_service.core.exceptions.ImageNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public ImageResponseDTO saveImage(MultipartFile file, ImageRequestDTO dto) {
        String storagePath = "uploads/" + file.getOriginalFilename();

        try {
            Path path = Paths.get(storagePath);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }

        Image image = new Image();
        image.setFileName(dto != null && dto.getFileName() != null
                ? dto.getFileName()
                : file.getOriginalFilename());
        image.setSize(String.valueOf(file.getSize()));
        image.setUrl(storagePath);

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

        if (dto.getFileName() != null)
            existing.setFileName(dto.getFileName());

        Image updated = imageRepository.save(existing);
        return new ImageResponseDTO(updated);
    }
}
