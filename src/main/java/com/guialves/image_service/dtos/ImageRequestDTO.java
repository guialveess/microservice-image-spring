package com.guialves.image_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ImageRequestDTO {

    @NotBlank(message = "A URL da imagem não pode ser vazia")
    private String url;

    @NotBlank(message = "O nome do arquivo é obrigatório")
    private String fileName;

    private String size;
}
