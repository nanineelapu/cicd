<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>${project.name} | Pipeline</title>
            <link rel="stylesheet" href="/static/css/style.css">
        </head>

        <body>
            <div class="container">
                <header>
                    <div style="display: flex; align-items: center; gap: 1rem">
                        <a href="/" style="color: var(--text-muted); text-decoration: none">← Back</a>
                        <h1>${project.name}</h1>
                    </div>
                    <button onclick="triggerBuild('${project.id}')" class="btn btn-primary">Run Build</button>
                </header>

                <div class="card">
                    <h2 style="margin-bottom: 1rem">Build History</h2>
                    <div class="history-list">
                        <c:forEach items="${project.builds}" var="build">
                            <div class="history-item">
                                <div>
                                    <strong>#${build.id}</strong>
                                    <span class="badge ${build.status == 'SUCCESS' ? 'badge-success' : 'badge-danger'}"
                                        style="margin-left: 0.5rem">
                                        ${build.status}
                                    </span>
                                    <div style="font-size: 0.8rem; color: var(--text-muted); margin-top: 0.25rem">
                                        ${build.timestamp} • Duration: ${build.duration}s
                                    </div>
                                    <c:if test="${not empty build.failureReason}">
                                        <div style="color: var(--danger); font-size: 0.8rem; margin-top: 0.25rem">
                                            Reason: ${build.failureReason}
                                        </div>
                                    </c:if>
                                </div>
                                <div>
                                    <c:choose>
                                        <c:when test="${build.status == 'SUCCESS' && build.deployment == null}">
                                            <button onclick="deploy('${build.id}')" class="btn btn-primary"
                                                style="padding: 0.5rem 1rem; font-size: 0.8rem">Deploy</button>
                                        </c:when>
                                        <c:when test="${build.deployment != null}">
                                            <span class="badge badge-warning">
                                                ${build.deployment.status}
                                            </span>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <script>
                function triggerBuild(projectId) {
                    fetch('/api/builds/trigger/' + projectId, { method: 'POST' })
                        .then(res => res.json())
                        .then(() => location.reload());
                }

                function deploy(buildId) {
                    fetch('/api/deploy/' + buildId, { method: 'POST' })
                        .then(res => {
                            if (res.ok) location.reload();
                            else res.text().then(alert);
                        });
                }
            </script>
        </body>

        </html>