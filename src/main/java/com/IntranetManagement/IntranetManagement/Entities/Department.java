package com.IntranetManagement.IntranetManagement.Entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public String DepartmentName;
    @Column(nullable = false)
    public int IsActive=1;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Event> events;

    // Relation OneToMany : un département peut avoir plusieurs utilisateurs
    @OneToMany(mappedBy = "department")
    private List<User> users = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

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
