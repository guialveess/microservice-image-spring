package com.guialves.image_service.infrastructure.controllers;

import com.guialves.image_service.application.dtos.ImageResponseDTO;
import com.guialves.image_service.application.dtos.ImageRequestDTO;
import com.guialves.image_service.application.usecases.ImageUseCase;
import com.guialves.image_service.core.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageUseCase imageUseCase;

    public ImageController(ImageUseCase imageUseCase) {
        this.imageUseCase = imageUseCase;
    }

    @GetMapping
    public Page<ImageResponseDTO> getAllImages(Pageable pageable) {
        return imageUseCase.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ImageResponseDTO getImageById(@PathVariable Long id) {
        return imageUseCase.getById(id);
    }

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ImageResponseDTO saveImage(
            @RequestPart("file") MultipartFile file,
            @RequestPart(value = "metadata", required = false) ImageRequestDTO dto
    ) {
        return imageUseCase.create(file, dto);
    }

    @DeleteMapping("/{id}")
    public ImageResponseDTO deleteImage(@PathVariable Long id) {
        return imageUseCase.delete(id);
    }

    @PatchMapping("/{id}")
    public ImageResponseDTO updateImage(@Valid @PathVariable Long id, @RequestBody ImageRequestDTO dto) {
        return imageUseCase.update(id, dto);
    }
}

