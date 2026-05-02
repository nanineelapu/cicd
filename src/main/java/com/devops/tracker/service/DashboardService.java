package com.devops.tracker.service;

import com.devops.tracker.repository.BuildRepository;
import com.devops.tracker.repository.DeploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private BuildRepository buildRepository;
    
    @Autowired
    private DeploymentRepository deploymentRepository;

    public Map<String, Object> getStats() {
        long totalBuilds = buildRepository.count();
        long successBuilds = buildRepository.findAll().stream()
                .filter(b -> "SUCCESS".equals(b.getStatus()))
                .count();
        long failedBuilds = totalBuilds - successBuilds;
        long deploymentsCount = deploymentRepository.count();
        
        double successRate = totalBuilds == 0 ? 0 : (double) successBuilds / totalBuilds * 100;
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBuilds", totalBuilds);
        stats.put("successRate", String.format("%.2f", successRate));
        stats.put("failedBuilds", failedBuilds);
        stats.put("deploymentsCount", deploymentsCount);
        
        return stats;
    }
}
