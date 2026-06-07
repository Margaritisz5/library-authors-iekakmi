package com.iekakmi.eshop_api.dataAccessLayer.services;

import com.iekakmi.eshop_api.dataAccessLayer.models.AuthorDTO;
import com.iekakmi.eshop_api.dataAccessLayer.models.BookDTO;
import com.iekakmi.eshop_api.domainLayer.models.Author;
import com.iekakmi.eshop_api.domainLayer.models.Book;
import com.iekakmi.eshop_api.domainLayer.repositories.AuthorRepository;
import com.iekakmi.eshop_api.domainLayer.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::toDTO).toList();
    }

    public BookDTO getBookById(Long id) {
        return toDTO(findBook(id));
    }

    public BookDTO createBook(BookDTO dto) {
        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Υπάρχει ήδη βιβλίο με αυτό το ISBN");
        }
        Book book = new Book(dto.getIsbn(), dto.getTitle(), dto.getCategory(), dto.getPublicationYear());
        book.setAuthors(getAuthorsFromIds(dto.getAuthorIds()));
        return toDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(Long id, BookDTO dto) {
        Book book = findBook(id);
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setCategory(dto.getCategory());
        book.setPublicationYear(dto.getPublicationYear());
        book.setAuthors(getAuthorsFromIds(dto.getAuthorIds()));
        return toDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.delete(findBook(id));
    }

    public BookDTO assignAuthorsToBook(Long bookId, Set<Long> authorIds) {
        Book book = findBook(bookId);
        book.setAuthors(getAuthorsFromIds(authorIds));
        return toDTO(bookRepository.save(book));
    }

    public Book findBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Δεν βρέθηκε βιβλίο με id: " + id));
    }

    private Set<Author> getAuthorsFromIds(Set<Long> authorIds) {
        if (authorIds == null || authorIds.isEmpty()) {
            return new HashSet<>();
        }
        return authorIds.stream()
                .map(id -> authorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Δεν βρέθηκε συγγραφέας με id: " + id)))
                .collect(Collectors.toSet());
    }

    private BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setCategory(book.getCategory());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setAuthorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()));
        dto.setAuthors(book.getAuthors().stream().map(this::authorToDTO).collect(Collectors.toSet()));
        return dto;
    }

    private AuthorDTO authorToDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        dto.setDateOfBirth(author.getDateOfBirth());
        return dto;
    }
}
