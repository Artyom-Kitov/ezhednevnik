package ru.nsu.fit.ezhednevnik.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.ezhednevnik.dto.TaskCreationRequestDto;
import ru.nsu.fit.ezhednevnik.dto.TaskDto;
import ru.nsu.fit.ezhednevnik.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> get() {
        return ResponseEntity.ok(taskService.get());
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskCreationRequestDto request) {
        return ResponseEntity.ok(taskService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@RequestBody TaskCreationRequestDto request, @PathVariable UUID id) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

}
