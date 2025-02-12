<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="com.submission.mis.onlinesubmission.Models.Student" %>
<% Student student = (Student) session.getAttribute("student"); %>
<html>
<head>
    <title>Student</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
    <h1>Welcome, <c:out value="${student.username}" /></h1>
    <h2>Your Information</h2>
    <p><strong>Email:</strong> <c:out value="${student.getEmail()}" /></p>
    <p><strong>Birthdate:</strong> <c:out value="${student.getBirthDate()}" /></p>

    <br><br><br>
    <h2>Assigned Works</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Work Title</th>
                <th>Submission</th>
                <th>Work ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="work" items="${assignment}">
                <tr>
                    <td><c:out value="${work.title}" /></td>
                    <td><a href="submit?workId=${work.id}">Submit</a></td>
                    <td><c:out value="${work.id}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
