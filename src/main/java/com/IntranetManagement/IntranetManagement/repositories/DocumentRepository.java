package com.IntranetManagement.IntranetManagement.repositories;

import com.IntranetManagement.IntranetManagement.Entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByDepartmentId(Long departmentId);  // Find documents by department ID
}
