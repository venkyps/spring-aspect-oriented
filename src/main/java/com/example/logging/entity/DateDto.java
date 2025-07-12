package com.example.logging.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class DateDto {
    @Id
    private Long id;
    private LocalDate date;

    public DateDto() {}
    public DateDto(LocalDate date) {
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}