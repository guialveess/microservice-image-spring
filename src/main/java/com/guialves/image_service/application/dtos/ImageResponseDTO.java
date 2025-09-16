package com.guialves.image_service.application.dtos;

import com.guialves.image_service.core.entities.Image;
import lombok.Data;

@Data
public class ImageResponseDTO {
    private Long id;
    private String url;
    private String fileName;
    private String size;

    public ImageResponseDTO(Image image) {
        this.id = image.getId();
        this.fileName = image.getFileName();
        this.size = image.getSize();

        String baseUrl = System.getenv().getOrDefault("APP_BASE_URL", "http://localhost:8080");

        this.url = baseUrl + "/files/" + image.getUrl()
                .replace("uploads/", "");
    }
}
