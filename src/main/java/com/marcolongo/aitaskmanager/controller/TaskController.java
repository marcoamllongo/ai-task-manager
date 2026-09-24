package com.marcolongo.aitaskmanager.controller;

import com.marcolongo.aitaskmanager.dto.CreateTaskRequest;
import com.marcolongo.aitaskmanager.dto.TaskResponse;
import com.marcolongo.aitaskmanager.dto.UpdateTaskRequest;
import com.marcolongo.aitaskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest request
    ) {

        TaskResponse response = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable UUID id
    ) {

        TaskResponse response = taskService.getTaskById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {

        return ResponseEntity.ok(
                taskService.getAllTasks()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskRequest request
    ) {

        TaskResponse response = taskService.updateTask(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID id
    ) {

        taskService.deleteTask(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}