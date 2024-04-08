package ru.nsu.fit.ezhednevnik.persistence.repository;

import org.springframework.stereotype.Component;
import ru.nsu.fit.ezhednevnik.persistence.entity.Task;

import java.util.*;

@Component
public class TaskRepository {

    private final Map<UUID, Task> tasks = new HashMap<>();

    public Optional<Task> findById(UUID id) {
        Task task = tasks.get(id);
        if (task == null) {
            return Optional.empty();
        }
        return Optional.of(task);
    }

    public void deleteById(UUID id) {
        tasks.remove(id);
    }

    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> findAll() {
        return tasks.values().stream().toList();
    }

}
