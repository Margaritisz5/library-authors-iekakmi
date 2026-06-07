package com.iekakmi.eshop_api.dataAccessLayer.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class AuthorDTO {
    private Long id;

    @NotBlank(message = "Το όνομα συγγραφέα είναι υποχρεωτικό")
    private String name;

    @NotBlank(message = "Η εθνικότητα είναι υποχρεωτική")
    private String nationality;

    @NotNull(message = "Η ημερομηνία γέννησης είναι υποχρεωτική")
    private LocalDate dateOfBirth;

    private Set<Long> bookIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public Set<Long> getBookIds() { return bookIds; }
    public void setBookIds(Set<Long> bookIds) { this.bookIds = bookIds; }
}
