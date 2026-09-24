package com.marcolongo.aitaskmanager.dto;

import com.marcolongo.aitaskmanager.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(

        UUID id,
        String title,
        String purpose,
        String description,
        LocalDateTime deadline,
        TaskStatus status,
        LocalDateTime createdAt

) {
}