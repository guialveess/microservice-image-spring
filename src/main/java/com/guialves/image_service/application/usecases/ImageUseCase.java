package com.guialves.image_service.application.usecases;

import com.guialves.image_service.application.dtos.ImageRequestDTO;
import com.guialves.image_service.application.dtos.ImageResponseDTO;
import com.guialves.image_service.core.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUseCase {

    private final ImageService imageService;

    public ImageUseCase(ImageService imageService) {
        this.imageService = imageService;
    }

    public Page<ImageResponseDTO> getAll(Pageable pageable) {
        return imageService.getAllImages(pageable);
    }

    public ImageResponseDTO getById(Long id) {
        return imageService.getImageById(id);
    }

    public ImageResponseDTO create(MultipartFile file, ImageRequestDTO dto) {
        return imageService.saveImage(file, dto);
    }

    public ImageResponseDTO delete(Long id) {
        return imageService.deleteImage(id);
    }

    public ImageResponseDTO update(Long id, ImageRequestDTO dto) {
        return imageService.patchImage(id, dto);
    }
}
