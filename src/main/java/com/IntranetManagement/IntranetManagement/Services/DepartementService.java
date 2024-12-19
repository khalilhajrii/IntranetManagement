package com.IntranetManagement.IntranetManagement.Services;
import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllActiveDepartments(){
        try{
        return departmentRepository.findAll();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    public Optional<Department> findDepartementById(Integer id){
        Optional<Department> departmentResult = Optional.empty();
        try{
            departmentResult= departmentRepository.findById(id);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return departmentResult;
    }

}
