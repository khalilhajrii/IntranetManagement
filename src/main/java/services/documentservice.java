package project.services;

import com.project.entities.Document;
import com.project.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    
    private final DocumentRepository documentRepository;
    
    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }
    
    public Document updateDocument(Document document) {
        return documentRepository.save(document);
    }
    
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
    
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
    
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }
}