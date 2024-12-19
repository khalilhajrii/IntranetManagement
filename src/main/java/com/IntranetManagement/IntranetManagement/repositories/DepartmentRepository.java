package com.IntranetManagement.IntranetManagement.repositories;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
