package com.ctc.itlease.taskmanager.project;


import com.ctc.itlease.taskmanager.model.UserDateAuditEntity;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@SQLDelete(sql = "update ctc_project set deleted=true where id=?")
@Loader(namedQuery = "findProjectById")
@NamedQuery(
        name = "findProjectById",
        query = "select p from Project p where p.id=?1 and p.deleted=false")
@Where(clause = "deleted=false")
public class Project extends UserDateAuditEntity {

    private String name;

    private String description;

    @OneToMany
    private Set<Task> tasks = new HashSet<>();

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
