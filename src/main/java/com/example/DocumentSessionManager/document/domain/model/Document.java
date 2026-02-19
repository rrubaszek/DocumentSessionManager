package com.example.DocumentSessionManager.document.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Getter
@Setter
public class Document {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(nullable = false)
    private Long version; // Version is incremented for each update to resolve conflicts between users

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant lastModifiedAt;

    protected Document() {}

    public Document(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.createdAt = Instant.now();
        this.lastModifiedAt = this.createdAt;
    }

    public void touch() {
        this.lastModifiedAt = Instant.now();
    }
}
