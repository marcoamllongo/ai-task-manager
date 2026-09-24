package com.marcolongo.aitaskmanager.service;

import com.marcolongo.aitaskmanager.dto.CreateTaskRequest;
import com.marcolongo.aitaskmanager.dto.TaskResponse;
import com.marcolongo.aitaskmanager.entity.Task;
import com.marcolongo.aitaskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(CreateTaskRequest request) {

        Task task = new Task(
                request.title(),
                request.purpose(),
                request.description(),
                request.deadline()
        );

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    public TaskResponse getTaskById(UUID id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found")
                );

        return toResponse(task);
    }

    public List<TaskResponse> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TaskResponse toResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getPurpose(),
                task.getDescription(),
                task.getDeadline(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }
}