package com.IntranetManagement.IntranetManagement.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Title is required")
    private String content;

    @NotNull(message = "isPrivate field is required")
    @Min(0) @Max(1)
    private Integer isPrivate; //1 private, 0 not private

    @NotNull(message = "isImportant field is required")
    @Min(0) @Max(1)
    private Integer isImportant;// 1 important, 0 not important

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
}
