package com.IntranetManagement.IntranetManagement.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String DepartmentName;

    @Column(name = "is_active", nullable = false)
    public int IsActive=1;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<User> users;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<com.IntranetManagement.IntranetManagement.Entities.news> news;

    public List<com.IntranetManagement.IntranetManagement.Entities.news> getNews() {
        return news;
    }

    public void setNews(List<com.IntranetManagement.IntranetManagement.Entities.news> news) {
        this.news = news;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

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
