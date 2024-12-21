package com.IntranetManagement.IntranetManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Content is required")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDate creationDate = LocalDate.now();


    @NotNull(message = "Highlighted is required")
    @Min(0) @Max(1)
    private Integer isHighlighted; // 1 highlighted

    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Department department;

    public Integer getId() {
        return id;
    }

    public @NotNull(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotNull(message = "Content is required") String getContent() {
        return content;
    }

    public void setContent(@NotNull(message = "Content is required") String content) {
        this.content = content;
    }

    public @NotNull LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NotNull LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public @NotNull(message = "Highlighted is required") @Min(0) @Max(1) Integer getIsHighlighted() {
        return isHighlighted;
    }

    public void setIsHighlighted(@NotNull(message = "Highlighted is required") @Min(0) @Max(1) Integer isHighlighted) {
        this.isHighlighted = isHighlighted;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
