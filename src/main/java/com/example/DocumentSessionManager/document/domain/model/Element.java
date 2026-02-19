package com.example.DocumentSessionManager.document.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Element {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "document_id", nullable = false)
    private UUID documentId;

    @Column(nullable = false)
    protected int x;

    @Column(nullable = false)
    protected int y;

    protected Element() {}

    protected Element(UUID documentId, int x, int y) {
        this.id = UUID.randomUUID();
        this.documentId = documentId;
        this.x = x;
        this.y = y;
    }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
