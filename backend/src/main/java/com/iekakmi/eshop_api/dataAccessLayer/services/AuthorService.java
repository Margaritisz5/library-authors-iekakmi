package com.iekakmi.eshop_api.dataAccessLayer.services;

import com.iekakmi.eshop_api.dataAccessLayer.models.AuthorDTO;
import com.iekakmi.eshop_api.dataAccessLayer.models.BookDTO;
import com.iekakmi.eshop_api.domainLayer.models.Author;
import com.iekakmi.eshop_api.domainLayer.models.Book;
import com.iekakmi.eshop_api.domainLayer.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(this::toDTO).toList();
    }

    public AuthorDTO getAuthorById(Long id) {
        return toDTO(findAuthor(id));
    }

    public AuthorDTO createAuthor(AuthorDTO dto) {
        Author author = new Author(dto.getName(), dto.getNationality(), dto.getDateOfBirth());
        return toDTO(authorRepository.save(author));
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO dto) {
        Author author = findAuthor(id);
        author.setName(dto.getName());
        author.setNationality(dto.getNationality());
        author.setDateOfBirth(dto.getDateOfBirth());
        return toDTO(authorRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        Author author = findAuthor(id);
        for (Book book : author.getBooks()) {
            book.getAuthors().remove(author);
        }
        authorRepository.delete(author);
    }

    public Set<BookDTO> getBooksOfAuthor(Long authorId) {
        return findAuthor(authorId).getBooks().stream().map(this::bookToDTO).collect(Collectors.toSet());
    }

    public Author findAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Δεν βρέθηκε συγγραφέας με id: " + id));
    }

    private AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        dto.setDateOfBirth(author.getDateOfBirth());
        dto.setBookIds(author.getBooks().stream().map(Book::getId).collect(Collectors.toSet()));
        return dto;
    }

    private BookDTO bookToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setCategory(book.getCategory());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setAuthorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()));
        return dto;
    }
}
