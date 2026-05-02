package com.devops.tracker.controller;

import com.devops.tracker.model.Build;
import com.devops.tracker.model.Deployment;
import com.devops.tracker.model.Project;
import com.devops.tracker.service.BuildService;
import com.devops.tracker.service.DeploymentService;
import com.devops.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private BuildService buildService;
    
    @Autowired
    private DeploymentService deploymentService;

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PostMapping("/builds/trigger/{projectId}")
    public Build triggerBuild(@PathVariable Long projectId) {
        return buildService.triggerBuild(projectId);
    }

    @PostMapping("/deploy/{buildId}")
    public ResponseEntity<?> deploy(@PathVariable Long buildId) {
        try {
            return ResponseEntity.ok(deploymentService.deploy(buildId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/builds/{projectId}")
    public List<Build> getBuildHistory(@PathVariable Long projectId) {
        return buildService.getBuildHistory(projectId);
    }
}
