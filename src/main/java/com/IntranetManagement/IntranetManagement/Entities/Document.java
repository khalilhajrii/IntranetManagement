package com.IntranetManagement.IntranetManagement.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Title is required")
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @NotNull(message = "isPrivate field is required")
    @Min(0) @Max(1)
    private Integer isPrivate;

    @NotNull(message = "isImportant field is required")
    @Min(0) @Max(1)
    private Integer isImportant;

    @ManyToOne
    @JoinColumn(name="dep_id", nullable = false)
    @JsonIgnore
    private Department department;


    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Title is required") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "Title is required") String content) {
        this.content = content;
    }

    public @NotNull(message = "isPrivate field is required") @Min(0) @Max(1) Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(@NotNull(message = "isPrivate field is required") @Min(0) @Max(1) Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public @NotNull(message = "isImportant field is required") @Min(0) @Max(1) Integer getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(@NotNull(message = "isImportant field is required") @Min(0) @Max(1) Integer isImportant) {
        this.isImportant = isImportant;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
