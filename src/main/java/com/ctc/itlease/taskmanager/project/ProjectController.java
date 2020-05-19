package com.ctc.itlease.taskmanager.project;

import com.ctc.itlease.taskmanager.project.Project;
import com.ctc.itlease.taskmanager.payload.ApiResponse;
import com.ctc.itlease.taskmanager.payload.PagedResponse;
import com.ctc.itlease.taskmanager.project.ProjectRequest;
import com.ctc.itlease.taskmanager.project.ProjectResponse;
import com.ctc.itlease.taskmanager.project.IProjectService;
import com.ctc.itlease.taskmanager.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping({"", "/list"})
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

    @GetMapping({"{projectId}", "update/{projectId}",})
    public ProjectResponse getProject(@PathVariable long projectId) {
        return projectService.findById(projectId);
    }

    @PostMapping("update/{projectId}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectRequest projectRequest) {
        Project project = projectService.update(projectRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{projectId}")
                .buildAndExpand(project.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Project updated successfully"));
    }

    @DeleteMapping({"{projectId}"})
    public ResponseEntity<?> deleteProject(@PathVariable long projectId) {
        try {
            projectService.deleteById(projectId);
            return ResponseEntity.ok(new ApiResponse(true, "Project deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
