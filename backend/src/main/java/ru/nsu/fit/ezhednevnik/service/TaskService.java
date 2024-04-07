package ru.nsu.fit.ezhednevnik.service;

import ru.nsu.fit.ezhednevnik.dto.TaskCreationRequestDto;
import ru.nsu.fit.ezhednevnik.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> get();

    TaskDto create(TaskCreationRequestDto request);

    TaskDto update(UUID id, TaskCreationRequestDto request);

    void delete(UUID id);

}
