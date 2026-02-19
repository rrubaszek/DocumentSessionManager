package com.example.DocumentSessionManager.document.domain.dto;

import java.util.UUID;

public record UpdateElementRequest(
        int x,
        int y,
        String text
) {}
