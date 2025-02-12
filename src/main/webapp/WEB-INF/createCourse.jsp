<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
    <h1>${formName} </h1>
    <form action="addCourse" method="POST">
        <p class="error">${error}</p>
        <div>
            <label>Course Name</label>
            <input type="text" name="course" placeholder="Enter the course name" />
        </div>
        <input type="submit" value="CreateCourse" />
    </form>

</body>
</html>
