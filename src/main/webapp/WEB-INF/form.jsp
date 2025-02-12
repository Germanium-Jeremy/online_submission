<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Controller Form</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
<h2>${formName}</h2>
<form action="registration.rca" method="POST">
    <label> FirstName</label><input type="text" name="firstName"><br>
    <label> LastName</label><input type="text" name="lastName"><br>
    <label> Email</label><input type="text" name="email"><br>
    <label> Birthdate</label><input type="date" name="dateOfBirth"><br>
    <label> Password</label><input type="password" name="password"><br>
    <input type="submit" value="Register">
</form>

<h2> List of students</h2>
    <table>
        <tr>
            <th> Id</th>
            <th> FirstName</th>
            <th> LastName</th>
            <th> Email</th>
            <th> Dob</th>
            <th> Age</th>

        </tr>
        <c:forEach var="student" items="${students}">
        <tr><td><c:out value="${student.id}"/></td>
            <td><c:out value="${student.firstName}"/></td>
            <td><c:out value="${student.lastName}"/></td>
            <td><c:out value="${student.email}"/></td>
            <td><c:out value="${student.birthDate}"/></td>
            <td><c:out value="${student.age}"/></td>
        </tr>

        </c:forEach>
    </table>
</body>
</html>