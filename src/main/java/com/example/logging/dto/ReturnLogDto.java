package com.example.logging.dto;

import lombok.Data;

@Data
public class ReturnLogDto {
    private String className;
    private String methodName;
    private Object returnValue;

    public ReturnLogDto(String className, String methodName, Object returnValue) {
        this.className = className;
        this.methodName = methodName;
        this.returnValue = returnValue;
    }
    // Getters and setters
}
