package com.devops.tracker.service;

import com.devops.tracker.model.Build;
import com.devops.tracker.model.Project;
import com.devops.tracker.repository.BuildRepository;
import com.devops.tracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class BuildService {
    @Autowired
    private BuildRepository buildRepository;
    
    @Autowired
    private ProjectRepository projectRepository;

    public Build triggerBuild(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        
        Build build = new Build();
        build.setProject(project);
        build.setTimestamp(LocalDateTime.now());
        
        // Simulation Logic
        Random random = new Random();
        boolean success = random.nextInt(100) > 20; // 80% success rate
        
        build.setStatus(success ? "SUCCESS" : "FAILED");
        build.setDuration((long) (random.nextInt(30) + 10)); // 10-40 seconds
        
        if (!success) {
            String[] reasons = {"Unit test failure", "Compilation error", "Dependency mismatch", "Out of memory"};
            build.setFailureReason(reasons[random.nextInt(reasons.length)]);
        }
        
        return buildRepository.save(build);
    }

    public List<Build> getBuildHistory(Long projectId) {
        return buildRepository.findByProjectId(projectId);
    }
    
    public Build getBuildById(Long id) {
        return buildRepository.findById(id).orElse(null);
    }
}
