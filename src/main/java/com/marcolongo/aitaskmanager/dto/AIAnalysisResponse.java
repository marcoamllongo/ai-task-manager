package com.marcolongo.aitaskmanager.dto;

public record AIAnalysisResponse(
        String priority,
        String reason,
        String suggestedAction
) {
}