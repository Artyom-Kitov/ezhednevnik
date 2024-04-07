package ru.nsu.fit.ezhednevnik.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "tasks")
@Setter
@Getter
@Accessors(chain = true)
public class Task {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    private String description;

    private String priority;

    private String status;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    private Instant deadline;

}
