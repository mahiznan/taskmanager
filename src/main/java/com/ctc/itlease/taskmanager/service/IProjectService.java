package com.ctc.itlease.taskmanager.service;

import com.ctc.itlease.taskmanager.model.Project;
import com.ctc.itlease.taskmanager.payload.PagedResponse;
import com.ctc.itlease.taskmanager.payload.ProjectRequest;
import com.ctc.itlease.taskmanager.payload.ProjectResponse;

public interface IProjectService {
    PagedResponse<ProjectResponse> listProjects(int page, int size);

    Project create(ProjectRequest projectRequest);
}
