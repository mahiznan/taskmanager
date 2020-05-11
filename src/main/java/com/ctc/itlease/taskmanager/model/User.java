package com.ctc.itlease.taskmanager.model;

import com.ctc.itlease.taskmanager.model.audit.UserDateAudit;
import com.ctc.itlease.taskmanager.util.Constants;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"})})
@SQLDelete(sql = "update ctc_user set deleted=true where id=?")
@Loader(namedQuery = "findUserById")
@NamedQuery(
        name = "findUserById",
        query = "select u from User u where u.id=?1 and u.deleted=false")
@Where(clause = "deleted=false")
public class User extends UserDateAudit implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    @Column(length = 40)
    private String name;
    @Column(length = 15)
    private String username;
    @Column(length = 40)
    private String email;
    @Column(length = 100)
    private String password;
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String username, String email, String password) {
        setName(name);
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        if (role == null) {
            throw new NullPointerException("Role can't be null");
        }
        this.roles.add(role);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
