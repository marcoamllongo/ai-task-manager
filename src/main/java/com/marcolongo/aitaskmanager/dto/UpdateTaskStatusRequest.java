package com.marcolongo.aitaskmanager.dto;

import com.marcolongo.aitaskmanager.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(

        @NotNull
        TaskStatus status

) {
}