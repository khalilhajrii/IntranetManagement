package com.IntranetManagement.IntranetManagement.Controller;



import com.IntranetManagement.IntranetManagement.Entities.Document;
import com.IntranetManagement.IntranetManagement.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments/{departmentId}/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // Create a new document
    @PostMapping
    public Document createDocument(@PathVariable Long departmentId,
                                   @RequestParam String title,
                                   @RequestParam String content) {
        return documentService.createDocument(departmentId, title, content);
    }

    // Update an existing document
    @PutMapping("/{documentId}")
    public Document updateDocument(@PathVariable Long documentId,
                                   @RequestParam String title,
                                   @RequestParam String content) {
        return documentService.updateDocument(documentId, title, content);
    }

    // Delete a document
    @DeleteMapping("/{documentId}")
    public void deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
    }

    // Get all documents for a specific department
    @GetMapping
    public List<Document> getDocuments(@PathVariable Long departmentId) {
        return documentService.getDocumentsForDepartment(departmentId);
    }
}
