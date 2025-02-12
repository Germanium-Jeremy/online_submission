<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="com.submission.mis.onlinesubmission.Models.Submission" %>
<%@ page import="java.util.List" %>
<%
    List<Submission> submissions = (List<Submission>) request.getAttribute("submissions");
//    long assignmentId = (long) request.getAttribute("assignmentId");
%>
<html>
<head>
    <title>View Submitted Works</title>
<%--    <title>Submissions for Assignment ID: <c:out value="${assignmentId}" /></title>--%>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
<h1>Submissions for Assignment</h1>
<table>
    <thead>
    <tr>
        <th>Student Username</th>
        <th>File Path</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="submission" items="${submissions}">
        <tr>
            <td><c:out value="${submission.getUsers().getUsername()}" /></td>
            <td>
                 See in the server in for a file
                : <c:out value="${submission.filePath}" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br><br>
<a href="mainDashboard">Back to Assignments</a>
</body>
</html>
