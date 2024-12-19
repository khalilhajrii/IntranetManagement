package com.IntranetManagement.IntranetManagement.repositories;

import com.IntranetManagement.IntranetManagement.Entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
