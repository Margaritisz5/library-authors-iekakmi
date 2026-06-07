package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
