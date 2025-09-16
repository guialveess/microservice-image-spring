package com.guialves.image_service.controllers;

import com.guialves.image_service.dtos.ImageResponseDTO;
import com.guialves.image_service.dtos.ImageRequestDTO;
import com.guialves.image_service.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public Page<ImageResponseDTO> getAllImages(Pageable pageable) {
        return imageService.getAllImages(pageable);
    }

    @GetMapping("/{id}")
    public ImageResponseDTO getImageById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @PostMapping
    public ImageResponseDTO saveImage(@Valid @RequestBody ImageRequestDTO dto) {
        return imageService.saveImage(dto);
    }

    @DeleteMapping("/{id}")
    public ImageResponseDTO deleteImage(@PathVariable Long id) {
        return imageService.deleteImage(id);
    }

    @PatchMapping("/{id}")
    public ImageResponseDTO updateImage(@Valid @PathVariable Long id, @RequestBody ImageRequestDTO dto) {
        return imageService.patchImage(id, dto);
    }
}
