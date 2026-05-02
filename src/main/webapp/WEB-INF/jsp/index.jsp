<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DevOps Tracker | Index</title>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700;800&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <header>
            <div>
                <h1>Mini CI/CD Tracker</h1>
                <p style="color: var(--text-muted)">Monitor your automated build and deployment pipelines</p>
            </div>
            <a href="/register" class="btn btn-primary">+ Register Project</a>
        </header>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-label">Total Builds</div>
                <div class="stat-value">${stats.totalBuilds}</div>
            </div>
            <div class="stat-card">
                <div class="stat-label">Success Rate</div>
                <div class="stat-value">${stats.successRate}%</div>
            </div>
            <div class="stat-card">
                <div class="stat-label">Deployments</div>
                <div class="stat-value">${stats.deploymentsCount}</div>
            </div>
            <div class="stat-card">
                <div class="stat-label">Failures</div>
                <div class="stat-value" style="color: var(--danger)">${stats.failedBuilds}</div>
            </div>
        </div>

        <h2 style="margin-bottom: 1.5rem">Active Projects</h2>
        <div class="project-grid">
            <c:forEach items="${projects}" var="project">
                <div class="card">
                    <h3>${project.name}</h3>
                    <span class="repo">${project.repoUrl}</span>
                    <div style="display: flex; gap: 1rem">
                        <a href="/project/${project.id}" class="btn" style="background: rgba(255,255,255,0.1); color: white">View Pipeline</a>
                        <button onclick="triggerBuild('${project.id}')" class="btn btn-primary">Run Build</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script>
        function triggerBuild(projectId) {
            fetch('/api/builds/trigger/' + projectId, { method: 'POST' })
                .then(res => res.json())
                .then(data => {
                    alert('Build triggered: ' + data.status);
                    location.reload();
                });
        }
    </script>
</body>
</html>
