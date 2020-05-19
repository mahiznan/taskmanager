package com.ctc.itlease.taskmanager.project;

import com.ctc.itlease.taskmanager.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
