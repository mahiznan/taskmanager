package com.ctc.itlease.taskmanager.model;


import com.ctc.itlease.taskmanager.util.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String name;
    private String description;
    @OneToMany
    private Set<Task> tasks = new HashSet<>();

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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new NullPointerException("Task can't be NULL");
        }
        if (task.getProject() != null) {
            throw new IllegalArgumentException("Task already assigned under another project");
        }
        getTasks().add(task);
        task.setProject(this);
    }
}
