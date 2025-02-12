<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${formName}</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>

    <h1>${formName}</h1>
    <form action="submit" method="POST" enctype="multipart/form-data">
        <p class="error">${error}</p>
        <div>
            <label>Upload file</label>
            <input type="file" name="file" required />
            <input type="hidden" name="assignmentId" value="${assignment.id}">
        </div>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
