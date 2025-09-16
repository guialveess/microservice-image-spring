package com.guialves.image_service.infrastructure.repository;

import com.guialves.image_service.core.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
