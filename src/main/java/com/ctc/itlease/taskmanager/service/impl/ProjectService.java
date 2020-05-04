package com.ctc.itlease.taskmanager.service.impl;

import com.ctc.itlease.taskmanager.model.Project;
import com.ctc.itlease.taskmanager.payload.PagedResponse;
import com.ctc.itlease.taskmanager.payload.ProjectRequest;
import com.ctc.itlease.taskmanager.payload.ProjectResponse;
import com.ctc.itlease.taskmanager.repository.ProjectRepository;
import com.ctc.itlease.taskmanager.service.IProjectService;
import com.ctc.itlease.taskmanager.util.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService implements IProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public PagedResponse<ProjectResponse> listProjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "name");
        Page<Project> projects = projectRepository.findAll(pageable);
        if (projects.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), projects.getNumber(), projects.getSize(),
                    projects.getTotalElements(), projects.getTotalPages(), projects.isLast());
        }
        List<ProjectResponse> projectResponses = projects.map(ModelMapper::mapProjectToProjectResponse).getContent();
        return new PagedResponse<>(projectResponses, projects.getNumber(), projects.getSize(), projects.getTotalElements(), projects.getTotalPages(), projects.isLast());
    }

    @Override
    public Project create(ProjectRequest projectRequest) {
        Project project = new Project();
        updateProject(project, projectRequest);
        return projectRepository.save(project);
    }

    private void updateProject(Project project, ProjectRequest projectRequest) {
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
    }

}
