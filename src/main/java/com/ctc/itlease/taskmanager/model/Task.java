package com.ctc.itlease.taskmanager.model;

import com.ctc.itlease.taskmanager.model.audit.UserDateAudit;
import com.ctc.itlease.taskmanager.util.Constants;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql = "update ctc_task set deleted=true where id=?")
@Loader(namedQuery = "findTaskById")
@NamedQuery(
        name = "findTaskById",
        query = "select t from Task t where t.id=?1 and t.deleted=false")
@Where(clause = "deleted=false")
public class Task extends UserDateAudit {
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
