package com.devops.tracker.controller;

import com.devops.tracker.service.DashboardService;
import com.devops.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @Autowired
    private DashboardService dashboardService;
    
    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("stats", dashboardService.getStats());
        model.addAttribute("projects", projectService.getAllProjects());
        return "dashboard";
    }

    @GetMapping("/project/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "project-details";
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
