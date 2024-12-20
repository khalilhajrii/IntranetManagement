package com.IntranetManagement.IntranetManagement.repositories;


import com.IntranetManagement.IntranetManagement.Entities.news;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<news, Integer> {


    List<news> findByDepartmentIdAndIsHighlighted(Integer departmentId, int i);
}
