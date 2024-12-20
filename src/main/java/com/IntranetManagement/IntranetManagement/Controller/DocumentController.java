package com.IntranetManagement.IntranetManagement.Controller;


import com.IntranetManagement.IntranetManagement.Entities.Document;
import com.IntranetManagement.IntranetManagement.Services.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {

        this.documentService = documentService;
    }


    // Get all important documents
    @GetMapping("/important/{departmentId}")
    public ResponseEntity<List<Document>> getImportantDocuments(@PathVariable Long departmentId) {
        List<Document> documents = documentService.getImportantDocuments(departmentId);
        return ResponseEntity.ok(documents);
    }


    // Get all public documents
    @GetMapping("/public/{departmentId}")
    public ResponseEntity<List<Document>> getPublicDocuments(@PathVariable Long departmentId) {
        List<Document> documents = documentService.getPublicDocuments(departmentId);
        return ResponseEntity.ok(documents);
    }

    // Get all private documents
    @GetMapping("/private/{departmentId}")
    public ResponseEntity<List<Document>> getPrivateDocuments(@PathVariable Long departmentId) {
        List<Document> documents = documentService.getPrivateDocuments(departmentId);
        return ResponseEntity.ok(documents);
    }
    // Get all private documents
    @GetMapping("/{documentId}")
    public ResponseEntity<Optional<Document>> getDocumentById(@PathVariable Long documentId) {
        Optional<Document> documents = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(documents);
    }

    // Get all private documents
    @GetMapping("/all/{departmentId}")
    public ResponseEntity<List<Document>> GetallDocuments(@PathVariable Long departmentId) {
        List<Document> documents = documentService.getAllDocuments(departmentId);
        return ResponseEntity.ok(documents);
    }

    // create
    @PostMapping("/create")
    public ResponseEntity<Document> createDocument(@RequestBody Document document,
                                             @RequestParam Integer departmentId) {

        return ResponseEntity.ok(documentService.createDocument(document, departmentId));
    }

    @PutMapping("/{documentId}/update")
    public ResponseEntity<Document> updateDocument(@PathVariable Long documentId,
                                             @RequestBody Document updatedDocument,
                                             @RequestParam Integer departmentId){
        Document document = documentService.updateDocument(documentId, updatedDocument, departmentId);
        return ResponseEntity.ok(document);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

}

