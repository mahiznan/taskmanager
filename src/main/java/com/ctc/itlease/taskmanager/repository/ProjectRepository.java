package com.ctc.itlease.taskmanager.repository;

import com.ctc.itlease.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
