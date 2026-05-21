package com.example.Library.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookRequestDto {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;
}
