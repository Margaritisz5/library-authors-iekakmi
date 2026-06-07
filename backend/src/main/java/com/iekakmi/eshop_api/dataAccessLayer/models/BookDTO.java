package com.iekakmi.eshop_api.dataAccessLayer.models;

import jakarta.validation.constraints.*;
import java.util.Set;

public class BookDTO {
    private Long id;

    @NotBlank(message = "Το ISBN είναι υποχρεωτικό")
    private String isbn;

    @NotBlank(message = "Ο τίτλος βιβλίου είναι υποχρεωτικός")
    private String title;

    @NotBlank(message = "Η κατηγορία είναι υποχρεωτική")
    private String category;

    @NotNull(message = "Το έτος έκδοσης είναι υποχρεωτικό")
    @Min(value = 1000, message = "Το έτος έκδοσης δεν είναι έγκυρο")
    @Max(value = 2100, message = "Το έτος έκδοσης δεν είναι έγκυρο")
    private Integer publicationYear;

    private Set<Long> authorIds;
    private Set<AuthorDTO> authors;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
    public Set<Long> getAuthorIds() { return authorIds; }
    public void setAuthorIds(Set<Long> authorIds) { this.authorIds = authorIds; }
    public Set<AuthorDTO> getAuthors() { return authors; }
    public void setAuthors(Set<AuthorDTO> authors) { this.authors = authors; }
}
