package com.example.logging.service;



import com.example.logging.annotation.Log;
import com.example.logging.entity.DateDto;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ClockService {
    @Log
    public DateDto getNextDate() {
        return new DateDto(LocalDate.now().plusDays(1));
    }
}