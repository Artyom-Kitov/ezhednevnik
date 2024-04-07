package ru.nsu.fit.ezhednevnik.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.ezhednevnik.dto.TaskCreationRequestDto;
import ru.nsu.fit.ezhednevnik.dto.TaskDto;
import ru.nsu.fit.ezhednevnik.persistence.entity.Task;
import ru.nsu.fit.ezhednevnik.persistence.repository.TaskRepository;
import ru.nsu.fit.ezhednevnik.service.TaskService;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> get() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public TaskDto create(TaskCreationRequestDto request) {
        Task task = new Task();
        return save(request, task);
    }

    @Override
    public TaskDto update(UUID id, TaskCreationRequestDto request) {
        Task task = taskRepository.findById(id).orElseThrow();
        return save(request, task);
    }

    @Override
    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    private TaskDto save(TaskCreationRequestDto request, Task task) {
        task.setName(request.name())
                .setDescription(request.description())
                .setPriority(request.priority())
                .setStatus(request.status());

        if (request.deadline() == null) {
            task.setDeadline(null);
        } else {
            LocalDate date = LocalDate.parse(request.deadline());
            task.setDeadline(date.atStartOfDay().toInstant(ZoneOffset.UTC));
        }
        return mapToDto(taskRepository.save(task));
    }

    private TaskDto mapToDto(Task task) {
        String deadlineString = null;
        if (task.getDeadline() != null) {
            var deadline = task.getDeadline().atZone(ZoneId.systemDefault());
            String year = String.format("%02d", deadline.getYear());
            String month = String.format("%02d", deadline.getMonthValue());
            String day = String.format("%02d", deadline.getDayOfMonth());
            deadlineString = year + "-" + month + "-" + day;
        }
        return TaskDto.builder()
                .id(task.getId())
                .createdAt(task.getCreatedAt().toString())
                .deadline(deadlineString)
                .description(task.getDescription())
                .name(task.getName())
                .priority(task.getPriority())
                .status(task.getStatus())
                .build();
    }

}
