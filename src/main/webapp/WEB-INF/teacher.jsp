<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="com.submission.mis.onlinesubmission.Models.Teachers" %>
<%@page import="com.submission.mis.onlinesubmission.Models.Assignment" %>
<%@ page import="java.util.List" %>
<%
    Teachers teacher = (Teachers) session.getAttribute("teacher");
    List<Assignment> assignments = (List<Assignment>) request.getAttribute("assignments");
%>
<html>
<head>
    <title>Teacher</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
<h1>Welcome, <c:out value="${teacher.username}" /></h1>
<h2>Your Information</h2>
<p><strong>Email:</strong> <c:out value="${teacher.email}" /></p>
<p><strong>Birthdate:</strong> <c:out value="${teacher.dateOfBirth}" /></p>

<div class="links">
    <a href="addCourse">Add course</a>
    <a href="assign">Create assignment</a>
</div>

<br><br><br>
<h2>Assigned Works</h2>
<table>
    <thead>
    <tr>
        <th>Assignments</th>
        <th>Submission</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="assign" items="${assignments}">
        <tr>
            <td><c:out value="${assign.title}" /></td>
            <td>
                <a href="viewSubmissions?assignmentId=${assign.id}">View Submitted Students</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
