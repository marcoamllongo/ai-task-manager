package com.marcolongo.aitaskmanager.entity;

import com.marcolongo.aitaskmanager.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Task() {
    }

    public Task(
            String title,
            String purpose,
            String description,
            LocalDateTime deadline
    ) {
        this.title = title;
        this.purpose = purpose;
        this.description = description;
        this.deadline = deadline;
        this.status = TaskStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}