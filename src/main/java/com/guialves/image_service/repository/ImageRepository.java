package com.guialves.image_service.repository;

import com.guialves.image_service.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
