package com.example.DocumentSessionManager.document.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "shape_elements")
@Getter
@Setter
public class ShapeElement extends Element {

    @Column(nullable = false)
    private ShapeType type;

    @Column
    private String text;

    protected ShapeElement() {
        super();
    }

    public ShapeElement(UUID documentId, int x, int y, ShapeType type, String text) {
        super(documentId, x, y);
        this.type = type;
        this.text = text;
    }

    public void updateText(String newText) {
        this.text = newText;
    }

}
