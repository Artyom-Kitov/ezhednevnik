package ru.nsu.fit.ezhednevnik.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.nsu.fit.ezhednevnik.dto.TaskCreationRequestDto;
import ru.nsu.fit.ezhednevnik.dto.TaskDto;
import ru.nsu.fit.ezhednevnik.persistence.entity.Task;
import ru.nsu.fit.ezhednevnik.service.TaskService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    private TaskCreationRequestDto createTestRequest() {
        return new TaskCreationRequestDto("test", "test", "critical", "to do", null);
    }

    @BeforeEach
    void cleanup() {
        List<TaskDto> tasks = taskService.get();
        for (TaskDto task : tasks) {
            taskService.delete(task.id());
        }
    }

    @Test
    void whenTaskCreated_sameEntityReturned() {
        // given
        TaskCreationRequestDto taskDto = createTestRequest();

        // when
        TaskDto created = taskService.create(taskDto);

        // then
        assertEquals(taskDto.name(), created.name());
        assertEquals(taskDto.description(), created.description());
        assertEquals(taskDto.status(), created.status());
        assertEquals(taskDto.priority(), created.priority());
        assertNotEquals(null, created.id());
    }

    @Test
    void whenTaskCreated_availableInGet() {
        // given
        TaskCreationRequestDto request = createTestRequest();

        // when
        taskService.create(request);
        TaskDto task = taskService.get().get(0);

        // then
        assertEquals(task.name(), request.name());
        assertEquals(task.description(), request.description());
        assertEquals(task.status(), request.status());
        assertEquals(task.priority(), request.priority());
    }

    @Test
    void whenTaskUpdated_updatedEntityReturned() {
        // given
        TaskCreationRequestDto creationRequest = createTestRequest();
        TaskCreationRequestDto editRequest = new TaskCreationRequestDto("name", "description",
                "high", "in progress", "2024-04-07");

        // when
        UUID id = taskService.create(creationRequest).id();
        TaskDto edited = taskService.update(id, editRequest);

        // then
        assertEquals(editRequest.name(), edited.name());
        assertEquals(editRequest.description(), edited.description());
        assertEquals(editRequest.priority(), edited.priority());
        assertEquals(editRequest.status(), edited.status());
        assertEquals(editRequest.deadline(), edited.deadline());
    }

    @Test
    void whenTaskDeleted_notAvailableInGet() {
        // given
        UUID id = taskService.create(createTestRequest()).id();

        // when
        taskService.delete(id);

        // then
        assertEquals(0, taskService.get().size());
    }

}