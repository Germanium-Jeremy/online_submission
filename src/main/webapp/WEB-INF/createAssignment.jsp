<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="com.submission.mis.onlinesubmission.Models.Courses" %>
<%@ page import="java.util.List" %>
<%
//    List<Courses> coursesList = (List<Courses>) request.getAttribute("courseList");
%>
<html>
<head>
    <title>${formName}</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
    <h1>${formName} </h1>
    <form action="assign" method="POST">
        <p class="error">${error}</p>
        <div>
            <label>Assignment Description</label>
            <input type="text" name="title" placeholder="Enter the assignment description" required />
        </div>
        <div>
            <label>Selct Course</label>
            <select name="courseId" required>
                <option value="">Select course</option>
                <c:forEach var="course" items="${courseList}">
                    <option value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" value="Create Assignment" />
    </form>
</body>
</html>
