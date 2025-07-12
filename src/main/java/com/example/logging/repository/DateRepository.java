package com.example.logging.repository;


import com.example.logging.entity.DateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<DateDto, Long> {}