package com.ctc.itlease.taskmanager.project;

import com.ctc.itlease.taskmanager.project.Project;
import com.ctc.itlease.taskmanager.model.UserDateAuditEntity;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@SQLDelete(sql = "update ctc_task set deleted=true where id=?")
@Loader(namedQuery = "findTaskById")
@NamedQuery(
        name = "findTaskById",
        query = "select t from Task t where t.id=?1 and t.deleted=false")
@Where(clause = "deleted=false")
public class Task extends UserDateAuditEntity {

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

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
