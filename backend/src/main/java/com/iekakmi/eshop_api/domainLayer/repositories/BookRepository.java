package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
