<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ODDSOCEAN ESPORTS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gaming.css">
</head>
<body>
    <div class="container">
        <div class="navbar">
            <div class="logo">OCN</div>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/">Home</a>
                <a href="${pageContext.request.contextPath}/login">Admin Login</a>
            </div>
        </div>

        <div class="hero-section">
            <h1>ODDSOCEAN</h1>
            <p>SINGLE PLACE FOR ALL TYPE OF TOURNAMENT CREATTION AND MANAGEMENT</p>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Get Started</a>
        </div>

        <div class="features-section">
            <div class="feature-card">
                <h3>🏆 Manage Teams</h3>
                <p>Create and manage your eSports teams with comprehensive player management.</p>
            </div>
            <div class="feature-card">
                <h3>📊 Track Tournaments</h3>
                <p>Organize tournaments, schedule matches, and track results in real-time.</p>
            </div>
            <div class="feature-card">
                <h3>🎯 Player Stats</h3>
                <p>Monitor player performance, K/D ratios, and skill ratings.</p>
            </div>
            <div class="feature-card">
                <h3>📈 Leaderboards</h3>
                <p>View team rankings and competitive standings dynamically.</p>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2025 ODDSOCEAN. All rights reserved.</p>
    </footer>
</body>
</html>
