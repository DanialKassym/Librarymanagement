package com.example.Library.domain.mapper;

import com.example.Library.controller.dto.BookRequestDto;
import com.example.Library.controller.dto.BookResponseDto;
import com.example.Library.domain.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookRequestDto dto);

    BookResponseDto toDto(Book book);
}