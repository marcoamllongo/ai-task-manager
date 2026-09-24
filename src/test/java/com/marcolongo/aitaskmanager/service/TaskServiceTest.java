package com.marcolongo.aitaskmanager.service;

import com.marcolongo.aitaskmanager.dto.CreateTaskRequest;
import com.marcolongo.aitaskmanager.dto.TaskResponse;
import com.marcolongo.aitaskmanager.entity.Task;
import com.marcolongo.aitaskmanager.exception.TaskNotFoundException;
import com.marcolongo.aitaskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AIAnalysisService aiAnalysisService;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldCreateTask() {

        // ARRANGE
        CreateTaskRequest request = new CreateTaskRequest(
                "Study Spring",
                "Prepare for backend interview",
                "Review Spring Boot services and controllers",
                LocalDateTime.now().plusDays(1)
        );

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        TaskResponse response = taskService.createTask(request);

        // ASSERT
        assertEquals("Study Spring", response.title());
        assertEquals("Prepare for backend interview", response.purpose());
        assertEquals("CREATED", response.status().name());

        verify(taskRepository, times(1))
                .save(any(Task.class));
    }

    @Test
    void shouldGetTaskById() {

        // ARRANGE
        UUID id = UUID.randomUUID();

        Task task = new Task(
                "Study Java",
                "Improve Java knowledge",
                "Review lambdas and streams",
                LocalDateTime.now().plusDays(2)
        );

        when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));

        // ACT
        TaskResponse response = taskService.getTaskById(id);

        // ASSERT
        assertEquals("Study Java", response.title());

        verify(taskRepository, times(1))
                .findById(id);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {

        // ARRANGE
        UUID id = UUID.randomUUID();

        when(taskRepository.findById(id))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskById(id)
        );

        verify(taskRepository, times(1))
                .findById(id);
    }
}