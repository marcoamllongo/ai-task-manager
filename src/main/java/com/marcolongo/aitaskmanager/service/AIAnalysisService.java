package com.marcolongo.aitaskmanager.service;

import com.marcolongo.aitaskmanager.dto.AIAnalysisResponse;
import com.marcolongo.aitaskmanager.entity.Task;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIAnalysisService {

    private final ChatClient chatClient;

    public AIAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public AIAnalysisResponse analyzeTask(Task task) {

        String taskInformation = """
                Title: %s
                Purpose: %s
                Description: %s
                Deadline: %s
                Status: %s
                """.formatted(
                task.getTitle(),
                task.getPurpose(),
                task.getDescription(),
                task.getDeadline() != null
                        ? task.getDeadline().toString()
                        : "No deadline defined",
                task.getStatus()
        );

        return chatClient
                .prompt()
                .system("""
                        You are a task analysis assistant.

                        Analyze the task using its purpose, description,
                        deadline and current status.

                        Classify priority as LOW, MEDIUM or HIGH.

                        Give a concise reason for the classification
                        and suggest one practical next action.
                        """)
                .user(taskInformation)
                .call()
                .entity(
                        AIAnalysisResponse.class,
                        spec -> spec
                                .useProviderStructuredOutput()
                                .validateSchema()
                );
    }
}