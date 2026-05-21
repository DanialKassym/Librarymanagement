package com.example.Library.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank
    private String title;

    @Positive
    private Integer quantity;
}
