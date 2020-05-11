package com.ctc.itlease.taskmanager.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProjectRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
