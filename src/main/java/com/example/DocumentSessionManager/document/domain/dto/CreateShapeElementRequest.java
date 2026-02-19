package com.example.DocumentSessionManager.document.domain.dto;

import com.example.DocumentSessionManager.document.domain.model.ShapeType;

import java.util.UUID;

public record CreateShapeElementRequest(
        int x,
        int y,
        String text,
        ShapeType type
) {}
