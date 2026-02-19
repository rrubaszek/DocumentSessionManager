package com.example.DocumentSessionManager.document.domain.dto;

import com.example.DocumentSessionManager.document.domain.model.Element;

import java.util.UUID;

public record CreateTextElementRequest (
        int x,
        int y,
        String text
) {}
