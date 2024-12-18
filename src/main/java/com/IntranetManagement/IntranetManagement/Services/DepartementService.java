package com.IntranetManagement.IntranetManagement.Services;
import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartementService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllActiveDepartements(){
        try{
        return departmentRepository.findAll();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

}
