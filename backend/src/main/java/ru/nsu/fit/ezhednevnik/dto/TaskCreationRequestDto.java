package ru.nsu.fit.ezhednevnik.dto;

public record TaskCreationRequestDto(
        String name,
        String description,
        String priority,
        String status,
        String deadline
) {
}
