package com.ctc.itlease.taskmanager.project;

import com.ctc.itlease.taskmanager.project.Project;

public class ProjectResponse {
    private Long id;
    private String name;
    private String description;

    public ProjectResponse(Project project) {
        setId(project.getId());
        setName(project.getName());
        setDescription(project.getDescription());
    }

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
