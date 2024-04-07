package ru.nsu.fit.ezhednevnik.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TaskDto(
        UUID id,
        String name,
        String description,
        String priority,
        String status,
        String createdAt,
        String deadline
) {
}
