package ru.nsu.fit.ezhednevnik.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
public class Task {

    private UUID id = UUID.randomUUID();

    private String name;

    private String description;

    private String priority;

    private String status;

    private Instant createdAt = Instant.now();

    private Instant deadline;

}
