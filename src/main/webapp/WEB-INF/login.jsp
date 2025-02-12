<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css" />
</head>
<body>
    <h1>${formName}</h1>
    <form action="authentication" method="POST">
        <p class="error">${error}</p>
        <div>
            <label>Email</label>
            <input type="email" name="email" placeholder="email@example.domain" />
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" placeholder="password" />
        </div>
        <input type="submit" value="Login" />
    </form>

    <p>Don't have account?</p>
    <a href="signup">Register</a>

</body>
</html>
