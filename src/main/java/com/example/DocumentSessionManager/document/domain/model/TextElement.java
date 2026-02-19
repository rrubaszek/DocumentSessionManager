package com.example.DocumentSessionManager.document.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "text_elements")
@Getter
@Setter
public class TextElement extends Element {

    @Column(nullable = false)
    private String text;

    protected TextElement() {
        super();
    }

    public TextElement(UUID documentId, int x, int y, String text) {
        super(documentId, x, y);
        this.text = text;
    }

    public void updateText(String newText) {
        this.text = newText;
    }
}
