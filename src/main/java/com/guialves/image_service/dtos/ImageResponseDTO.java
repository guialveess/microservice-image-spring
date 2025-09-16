package com.guialves.image_service.dtos;

import com.guialves.image_service.entities.Image;
import lombok.Data;

@Data
public class ImageResponseDTO {
    private Long id;
    private String url;
    private String fileName;
    private String size;

    public ImageResponseDTO(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
        this.fileName = image.getFileName();
        this.size = image.getSize();
    }
}
