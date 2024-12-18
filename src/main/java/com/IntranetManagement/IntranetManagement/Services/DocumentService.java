package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.Document;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DocumentRepository documentRepository;

    // Create a new document for a department
    public Document createDocument(Long departmentId, String title, String content) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        Document document = new Document();
        document.setTitle(title);
        document.setContent(content);
        document.setDepartment(department);

        return documentRepository.save(document);
    }

    // Update a document
    public Document updateDocument(Long documentId, String title, String content) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        document.setTitle(title);
        document.setContent(content);

        return documentRepository.save(document);
    }

    // Delete a document
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    // Get all documents for a specific department
    public List<Document> getDocumentsForDepartment(Long departmentId) {
        return documentRepository.findByDepartmentId(departmentId);
    }
}
