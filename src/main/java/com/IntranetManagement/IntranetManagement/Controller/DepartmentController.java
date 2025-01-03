package com.IntranetManagement.IntranetManagement.Controller;


import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    // Créer un département
    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody String name,
                                                       @RequestParam Integer isActive) {
        Department department = departmentService.createDepartment(name,isActive);
        return ResponseEntity.ok(department);
    }


    // Mettre à jour un département
    @PutMapping("/{departmentId}/update")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer departmentId,
                                                       @RequestBody String newName,
                                                       @RequestParam Long userId) {
        Department updatedDepartment = departmentService.updateDepartment(departmentId, newName, userId);
        return ResponseEntity.ok(updatedDepartment);
    }


    // Supprimer un département
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }


    // Affecter un utilisateur à un département
 /*   @PostMapping("/{departmentId}/assign")
    public ResponseEntity<Void> assignUserToDepartment(@RequestParam Long userId,
                                                       @PathVariable Integer departmentId,
                                                       @RequestParam Long adminId) {
        departmentService.assignUserToDepartment(userId, departmentId, adminId);
        return ResponseEntity.ok().build();
    }*/


    /*
    @PostMapping("/{departmentId}/remove")
    public ResponseEntity<Void> removeUserFromDepartment(@RequestParam Long userId,
                                                         @PathVariable Integer departmentId,
                                                         @RequestParam Long adminId) {
        departmentService.removeUserFromDepartment(userId, departmentId, adminId);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }





}
