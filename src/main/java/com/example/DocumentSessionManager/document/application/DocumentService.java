package com.example.DocumentSessionManager.document.application;

import com.example.DocumentSessionManager.document.domain.model.*;
import com.example.DocumentSessionManager.document.infrastracture.persistance.DocumentRepository;
import com.example.DocumentSessionManager.document.infrastracture.persistance.ElementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    public final DocumentRepository documentRepository;
    public final ElementRepository elementRepository;

    public DocumentService(DocumentRepository documentRepository, ElementRepository elementRepository) {
        this.documentRepository = documentRepository;
        this.elementRepository = elementRepository;
    }

    public Document createDocument(String title) {
        Document document = new Document(title);

        return documentRepository.save(document);
    }

    @Transactional
    public TextElement addTextElement(UUID documentId, int x, int y, String text) {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));

        TextElement textElement = new TextElement(documentId, x, y, text);
        elementRepository.save(textElement);

        document.touch();
        documentRepository.save(document);

        return textElement;
    }

    @Transactional
    public ShapeElement addShapeElement(UUID documentId, int x, int y, ShapeType type, String text) {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));

        ShapeElement shapeElement = new ShapeElement(documentId, x, y, type, text);
        elementRepository.save(shapeElement);

        document.touch();
        documentRepository.save(document);

        return shapeElement;
    }

    @Transactional
    public Element updateElement(UUID documentId, UUID elementId, Optional<Integer> newX,
                                 Optional<Integer> newY, Optional<String> newText) {

        Element element = elementRepository.findByIdAndDocumentId(elementId, documentId)
                .orElseThrow(() -> new ElementNotFoundException(elementId));

        if (newX.isPresent() || newY.isPresent()) {
            int x = newX.orElse(element.getX());
            int y = newY.orElse(element.getY());
            element.move(x, y);
        }

        if (element instanceof TextElement textElement && newText.isPresent()) {
            textElement.updateText(newText.get());
        } else if (element instanceof ShapeElement shapeElement && newText.isPresent()) {
            shapeElement.updateText(newText.get());
        }

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));

        document.touch();

        return element;
    }
}
