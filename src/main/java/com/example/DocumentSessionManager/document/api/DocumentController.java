package com.example.DocumentSessionManager.document.api;

import com.example.DocumentSessionManager.document.application.DocumentService;
import com.example.DocumentSessionManager.document.domain.dto.*;
import com.example.DocumentSessionManager.document.domain.model.Document;
import com.example.DocumentSessionManager.document.domain.model.Element;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public DocumentDto getDocumentByTitle(@PathVariable UUID id) {
        return map(documentService.getDocument(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentDto createDocument(@RequestBody CreateDocumentRequest request) {
        return map(documentService.createDocument(request.title()));
    }

    @PostMapping("/{id}/elements/text")
    @ResponseStatus(HttpStatus.CREATED)
    public ElementDto createTextElement(@PathVariable UUID id, @RequestBody CreateTextElementRequest request) {
        Element element = documentService.addTextElement(id, request.x(), request.y(), request.text());
        return map(element);
    }

    @PostMapping("/{id}/elements/shape")
    @ResponseStatus(HttpStatus.CREATED)
    public ElementDto createShapeElement(@PathVariable UUID id, @RequestBody CreateShapeElementRequest request) {
        Element element = documentService.addShapeElement(id, request.x(), request.y(), request.type(), request.text());
        return map(element);
    }

    @PatchMapping("/{id}/elements/{elementId}")
    public ElementDto updateElement(@PathVariable UUID id, @PathVariable UUID elementId, @RequestBody UpdateElementRequest request) {
        return map(documentService.updateElement(
                id,
                elementId,
                Optional.of(request.x()),
                Optional.of(request.y()),
                Optional.of(request.text())
        ));
    }

    private DocumentDto map(Document document) {
        return new DocumentDto(
                document.getTitle(),
                document.getId(),
                document.getLastModifiedAt(),
                document.getCreatedAt()
        );
    }

    private ElementDto map(Element element) {
        return new ElementDto(
                element.getId(),
                element.getDocumentId(),
                element.getX(),
                element.getY()
        );
    }
}
