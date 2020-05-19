package com.ctc.itlease.taskmanager.util;

import com.ctc.itlease.taskmanager.project.Project;
import com.ctc.itlease.taskmanager.project.ProjectResponse;

public class ModelMapper {
    public static ProjectResponse mapProjectToProjectResponse(Project project) {
        return new ProjectResponse(project);
    }
}
