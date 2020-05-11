package com.ctc.itlease.taskmanager.repository;

import com.ctc.itlease.taskmanager.model.Role;
import com.ctc.itlease.taskmanager.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);

}
