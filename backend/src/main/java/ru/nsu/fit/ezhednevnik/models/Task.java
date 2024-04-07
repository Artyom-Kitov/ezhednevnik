package ru.nsu.fit.ezhednevnik.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private long id;

    @NotEmpty(message = "Name could not be empty!")
    @Size(min = 1, max = 64, message = "Name must have a length from 1 to 64 characters!")
    private String name;

    @Max(value = 512, message = "Description must have a length of 512 characters maximum!")
    private String description;

    private String priority;
    private String status;

    private Date createdAt;
    private Date deadline;
}
