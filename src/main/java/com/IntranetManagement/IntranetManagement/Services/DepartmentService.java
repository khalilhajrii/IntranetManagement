package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentService(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    public Department createDepartment(String name) {
        Department department = new Department();
        department.setDepartmentName(name);
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department updateDepartment(Integer departmentId, String newName, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getIsAdmin() == null || user.getIsAdmin() != 1) {
            throw new RuntimeException("Only admins can update departments.");
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setDepartmentName(newName);
        return departmentRepository.save(department);
    }


    public void deleteDepartment(Integer departmentId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getIsAdmin() == null || user.getIsAdmin() != 1) {
            throw new RuntimeException("Only admins can delete departments.");
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }





    public void assignUserToDepartment(Long userId, Integer departmentId, Long adminId) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (admin.getIsAdmin() == null || admin.getIsAdmin() != 1) {
            throw new RuntimeException("Only admins can assign users to departments.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Vérifier si l'utilisateur est déjà affecté à un département
        if (user.getDepartment() != null) {
            throw new RuntimeException("User is already assigned to a department.");
        }

        user.setDepartment(department);
        userRepository.save(user);
    }



    public void removeUserFromDepartment(Long userId, Integer departmentId, Long adminId) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (admin.getIsAdmin() == null || admin.getIsAdmin() != 1) {
            throw new RuntimeException("Only admins can remove users from departments.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Vérifier si l'utilisateur appartient au département
        if (user.getDepartment() != department) {
            throw new RuntimeException("User is not part of this department.");
        }

        user.setDepartment(null); // Supprimer l'affectation du département
        userRepository.save(user);
    }






}
