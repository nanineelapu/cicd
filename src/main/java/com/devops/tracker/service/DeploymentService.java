package com.devops.tracker.service;

import com.devops.tracker.model.Build;
import com.devops.tracker.model.Deployment;
import com.devops.tracker.repository.BuildRepository;
import com.devops.tracker.repository.DeploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class DeploymentService {
    @Autowired
    private DeploymentRepository deploymentRepository;
    
    @Autowired
    private BuildRepository buildRepository;

    public Deployment deploy(Long buildId) {
        Build build = buildRepository.findById(buildId).orElseThrow();
        
        if (!"SUCCESS".equals(build.getStatus())) {
            throw new RuntimeException("Cannot deploy a failed build!");
        }
        
        Deployment deployment = new Deployment();
        deployment.setBuild(build);
        deployment.setTimestamp(LocalDateTime.now());
        
        // Simulation logic for deployment
        Random random = new Random();
        int result = random.nextInt(100);
        
        if (result > 15) {
            deployment.setStatus("DEPLOYED");
        } else if (result > 5) {
            deployment.setStatus("FAILED");
        } else {
            deployment.setStatus("ROLLED_BACK");
        }
        
        return deploymentRepository.save(deployment);
    }
}
