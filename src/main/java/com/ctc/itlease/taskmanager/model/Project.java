package com.ctc.itlease.taskmanager.model;


import com.ctc.itlease.taskmanager.model.audit.DateAudit;
import com.ctc.itlease.taskmanager.model.audit.UserDateAudit;
import com.ctc.itlease.taskmanager.util.Constants;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@SQLDelete(sql = "update ctc_project set deleted=true where id=?")
@Loader(namedQuery = "findProjectById")
@NamedQuery(
        name = "findProjectById",
        query = "select p from Project p where p.id=?1 and p.deleted=false")
@Where(clause = "deleted=false")
public class Project extends UserDateAudit {
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
