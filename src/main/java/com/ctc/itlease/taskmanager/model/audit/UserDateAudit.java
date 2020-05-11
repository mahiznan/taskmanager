package com.ctc.itlease.taskmanager.model.audit;

import com.ctc.itlease.taskmanager.model.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

@MappedSuperclass
public class UserDateAudit extends DateAudit {
    /*@CreatedBy
    @Column(updatable = false, columnDefinition = "varchar(15) references ctc_user(username)")
    private String createdBy;

    @LastModifiedBy
    @Column(columnDefinition = "varchar(15) references ctc_user(username)")
    private String modifiedBy;*/

    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", referencedColumnName = "username")
    private User createdBy;

    @LastModifiedBy
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifiedBy", referencedColumnName = "username")
    private User modifiedBy;

    public User getCreatedBy() {
        return createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }
}
