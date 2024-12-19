package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.Document;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.DocumentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DepartmentRepository departmentRepository;

    public DocumentService(DocumentRepository documentRepository, DepartmentRepository departmentRepository) {
        this.documentRepository = documentRepository;
        this.departmentRepository = departmentRepository;
    }

    // Fetch
    // all public documents by department
    public List<Document> getPublicDocuments(Long departmentId) {
        return documentRepository.findAll().stream()
                .filter(doc -> doc.getIsPrivate() == 0 && doc.getDepartment().getId().equals(departmentId) )
                .collect(Collectors.toList());
    }

    // all private documents by department
    public List<Document> getPrivateDocuments(Long departmentId) {
        return documentRepository.findAll().stream()
                .filter(doc -> doc.getIsPrivate() == 1 && doc.getDepartment().getId().equals(departmentId))
                .collect(Collectors.toList());
    }

    // important documents
    public List<Document> getImportantDocuments(Long departmentId) {
        return documentRepository.findAll().stream()
                .filter(doc -> doc.getIsImportant() == 1 && doc.getDepartment().getId().equals(departmentId))
                .collect(Collectors.toList());
    }


    // Create
    public Document createDocument(Document document, Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        document.setDepartment(department);
        return documentRepository.save(document);
    }

    // Update
    public Document updateDocument(Long id, Document updatedDocument, Long departmentId) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("document not found"));

        document.setTitle(updatedDocument.getTitle());
        document.setContent(updatedDocument.getContent());
        document.setIsPrivate(updatedDocument.getIsPrivate());
        document.setIsImportant(updatedDocument.getIsImportant());

        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new RuntimeException("Department not found."));
            document.setDepartment(department);}

        return documentRepository.save(document);
    }

    // Delete a document
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}


