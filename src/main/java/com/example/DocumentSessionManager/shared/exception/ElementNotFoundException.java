package com.example.DocumentSessionManager.shared.exception;

import java.util.UUID;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(UUID id) {
        super("Element not found with id: " + id);
    }
}
