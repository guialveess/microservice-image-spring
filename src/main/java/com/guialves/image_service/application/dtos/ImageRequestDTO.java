package com.guialves.image_service.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ImageRequestDTO {

    @NotBlank(message = "O nome do arquivo é obrigatório")
    private String fileName;

    private String description;
}
