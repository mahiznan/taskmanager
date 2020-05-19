package com.ctc.itlease.taskmanager.project;

import com.ctc.itlease.taskmanager.payload.PagedResponse;

public interface IProjectService {
    Project create(ProjectRequest projectRequest);

    ProjectResponse findById(long projectId);

    PagedResponse<ProjectResponse> listProjects(int page, int size);

    Project update(ProjectRequest projectRequest);

    void deleteById(long projectId);
}
