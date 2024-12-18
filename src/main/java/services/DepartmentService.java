package project.services;

import com.project.entities.Department;
import com.project.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);public Department updateDepartment(Department department) {
            return departmentRepository.save(department);
        }
        
        public void deleteDepartment(Long id) {
            departmentRepository.deleteById(id);
        }
        
        public List<Department> getAllDepartments() {
            return departmentRepository.findAll();
        }
        
        public Department getDepartmentById(Long id) {
            return departmentRepository.findById(id).orElseThrow();
        }
