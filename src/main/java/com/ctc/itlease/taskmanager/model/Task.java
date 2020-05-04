package com.ctc.itlease.taskmanager.model;

import com.ctc.itlease.taskmanager.util.Constants;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String name;
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

    public Long getId() {
        return id;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
