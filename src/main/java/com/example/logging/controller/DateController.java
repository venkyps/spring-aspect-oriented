package com.example.logging.controller;

import com.example.logging.annotation.Log;
import com.example.logging.entity.DateDto;
import com.example.logging.repository.DateRepository;
import com.example.logging.service.ClockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Date API", description = "Operations related to date management")
@RestController
@RequestMapping("/api/date")
public class DateController {

    @Autowired
    private ClockService clockService;

    @Autowired
    private DateRepository dateRepository;

    @Operation(summary = "Get the next date", description = "Returns the next calendar date")
    @GetMapping("/next")
    @Log
    public DateDto getNextDate() {
        return clockService.getNextDate();
    }

    @Operation(summary = "Save a date", description = "Persists a date to the database")
    @PostMapping("/save")
    @Log
    public DateDto saveDate(@RequestBody DateDto dateDto) {
        return dateRepository.save(dateDto);
    }

    @Operation(summary = "Fetch all dates", description = "Retrieve all dates value from database")
    @GetMapping("/all")
    public Iterable<DateDto> getAllDates() {
        return dateRepository.findAll();
    }
}
