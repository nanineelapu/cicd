package com.devops.tracker.controller;

import com.devops.tracker.service.DashboardService;
import com.devops.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "index";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "App is running! If you see this, the problem is your JSP configuration.";
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
