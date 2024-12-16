package com.IntranetManagement.IntranetManagement.Entities;
import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public String DepartmentName;
    @Column(nullable = false)
    public int IsActive=1;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public int getIsActive() {
        return IsActive;
    }
}
