package com.example.logging.dto;

import lombok.Data;

@Data
public class EntitySaveLogDto {
    private String className;
    private Object entity;

    public EntitySaveLogDto(String className, Object entity) {
        this.className = className;
        this.entity = entity;
    }
    // Getters and setters
}
