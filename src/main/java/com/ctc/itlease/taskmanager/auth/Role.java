package com.ctc.itlease.taskmanager.auth;

import com.ctc.itlease.taskmanager.util.Constants;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    private RoleName name;

    public Long getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
