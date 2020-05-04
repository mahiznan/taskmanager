package com.ctc.itlease.taskmanager.controller;

import com.ctc.itlease.taskmanager.model.Project;
import com.ctc.itlease.taskmanager.payload.ApiResponse;
import com.ctc.itlease.taskmanager.payload.PagedResponse;
import com.ctc.itlease.taskmanager.payload.ProjectRequest;
import com.ctc.itlease.taskmanager.payload.ProjectResponse;
import com.ctc.itlease.taskmanager.service.IProjectService;
import com.ctc.itlease.taskmanager.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping({"", "/list"})
    public PagedResponse<ProjectResponse> list(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        return projectService.listProjects(page, size);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest projectRequest) {
        Project project = projectService.create(projectRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{projectId}")
                .buildAndExpand(project.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Project created successfully"));
    }

}
