<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Project</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container" style="max-width: 600px">
        <header>
            <h1>New Project</h1>
            <a href="/" style="color: var(--text-muted); text-decoration: none">Cancel</a>
        </header>

        <div class="card">
            <form id="projectForm">
                <div class="form-group">
                    <label>Project Name</label>
                    <input type="text" id="projectName" placeholder="e.g. My Awesome App" required>
                </div>
                <div class="form-group">
                    <label>Repository URL</label>
                    <input type="url" id="repoUrl" placeholder="https://github.com/user/repo" required>
                </div>
                <div class="form-group">
                    <label>Owner</label>
                    <input type="text" id="owner" placeholder="Development Team" required>
                </div>
                <button type="submit" class="btn btn-primary" style="width: 100%; justify-content: center">Create Project</button>
            </form>
        </div>
    </div>

    <script>
        document.getElementById('projectForm').onsubmit = function(e) {
            e.preventDefault();
            const data = {
                name: document.getElementById('projectName').value,
                repoUrl: document.getElementById('repoUrl').value,
                owner: document.getElementById('owner').value
            };

            fetch('/api/projects', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            }).then(() => window.location.href = '/');
        };
    </script>
</body>
</html>
