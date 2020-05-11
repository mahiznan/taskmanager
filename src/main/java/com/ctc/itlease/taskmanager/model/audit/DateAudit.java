package com.ctc.itlease.taskmanager.model.audit;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class DateAudit {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdDate;
    @LastModifiedDate
    @Column(nullable = false)
    private Instant modifiedDate;

    private boolean deleted;

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }


}
