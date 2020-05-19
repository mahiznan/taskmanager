package com.ctc.itlease.taskmanager.model;

import com.ctc.itlease.taskmanager.util.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private boolean deleted;

    public Long getId() {
        return id;
    }
}
