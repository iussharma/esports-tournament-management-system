<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ODDSOCEAN ADMIN</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gaming.css">
</head>
<body class="login-bg">
    <div class="login-container">
        <div class="login-box">
            <h1>ODDSOCEAN</h1>
            <h2>Admin Login</h2>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">${errorMessage}</div>
            </c:if>

            <form method="POST" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required placeholder="Enter username">
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required placeholder="Enter password">
                </div>

                <button type="submit" class="btn btn-primary btn-full">Login</button>
            </form>

            <div class="login-footer">
                <p>ODDSOCEAN ESPORTS</p>
                <p>Username: <strong>AYUSH1234</strong></p>
                <p>Password: <strong>Ayush@2908</strong></p>
            </div>

            <div class="back-link">
                <a href="${pageContext.request.contextPath}/">← Back to Home</a>
            </div>
        </div>
    </div>
</body>
</html>
