package com.example.DocumentSessionManager.document.domain.dto;

import java.time.Instant;
import java.util.UUID;

public record DocumentDto(
   String title,
   UUID id,
   Instant lastModifiedAt,
   Instant createdAt
) {}
