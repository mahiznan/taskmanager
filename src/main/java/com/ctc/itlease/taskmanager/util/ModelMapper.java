package com.ctc.itlease.taskmanager.util;

import com.ctc.itlease.taskmanager.model.Project;
import com.ctc.itlease.taskmanager.payload.ProjectResponse;

public class ModelMapper {
    public static ProjectResponse mapProjectToProjectResponse(Project project) {
        return new ProjectResponse(project);
    }
}
