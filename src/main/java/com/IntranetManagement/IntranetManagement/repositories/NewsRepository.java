package com.IntranetManagement.IntranetManagement.repositories;


import com.IntranetManagement.IntranetManagement.Entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {


    List<News> findByDepartmentIdAndIsHighlighted(Integer departmentId, int i);
}
