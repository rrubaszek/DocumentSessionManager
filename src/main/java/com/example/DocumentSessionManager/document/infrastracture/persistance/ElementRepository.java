package com.example.DocumentSessionManager.document.infrastracture.persistance;

import com.example.DocumentSessionManager.document.domain.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ElementRepository extends JpaRepository<Element, UUID> {
    public Optional<Element> findByIdAndDocumentId(UUID elementId, UUID documentId);
}
