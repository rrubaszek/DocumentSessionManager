package com.example.DocumentSessionManager.document.domain.dto;

import jakarta.persistence.Column;

import java.util.UUID;

public record ElementDto(
        UUID id,
        UUID documentId,
        int x,
        int y
) {}
