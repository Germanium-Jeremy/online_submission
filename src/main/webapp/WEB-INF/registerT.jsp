<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
    <h1>${formName}</h1>
    <form action="register" method="POST">
        <p class="error" style="color: red">${error}</p>
        <div>
            <label>Username</label>
            <input type="text" name="username" placeholder="Ngwijimbaraga Kalisa" />
        </div>
        <div>
            <label>Email</label>
            <input type="email" name="email" placeholder="email@example.domain" />
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" placeholder="password" />
        </div>
        <div>
            <label>Birthdate</label>
            <input type="date" name="birthdate" />
        </div>
        <div>
            <label>Role</label>
            <select name="role">
                <option value="Teacher">Teacher</option>
                <option value="Student">Student</option>
            </select>
        </div>
        <input type="submit" value="RegisterT" />
    </form>

    <c:if test="${not empty param.error}">
        <p style="color: red">${param.error}</p>
    </c:if>
</body>
</html>
