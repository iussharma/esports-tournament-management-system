<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="common/header.jsp" />

<div class="container">
    <div class="panel">
    <h1>Dashboard</h1>
    <p>RADHE RADHE <strong>${adminUsername}</strong>!</p>

    <div class="stats-section">
        <div class="stat-card stat-teams">
            <h3>Total Teams</h3>
            <p class="stat-number">${totalTeams}</p>
            <a href="${pageContext.request.contextPath}/team?action=list" class="card-link">View All</a>
        </div>

        <div class="stat-card stat-tournaments">
            <h3>Total Tournaments</h3>
            <p class="stat-number">${totalTournaments}</p>
            <a href="${pageContext.request.contextPath}/tournament?action=list" class="card-link">View All</a>
        </div>

        <div class="stat-card stat-matches">
            <h3>Quick Actions</h3>
            <ul class="quick-actions">
                <li><a href="${pageContext.request.contextPath}/team?action=form">+ New Team</a></li>
                <li><a href="${pageContext.request.contextPath}/player?action=form">+ New Player</a></li>
                <li><a href="${pageContext.request.contextPath}/match?action=schedule">+ Schedule Match</a></li>
                <li><a href="${pageContext.request.contextPath}/tournament?action=form">+ New Tournament</a></li>
            </ul>
        </div>
    </div>
</div>

    <h2>Top 5 Teams (by Points)</h2>
    <c:if test="${not empty topTeams}">
        <table class="data-table">
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>Team Name</th>
                    <th>Points</th>
                    <th>Wins</th>
                    <th>Losses</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${topTeams}" var="team" varStatus="status">
                    <tr>
                        <td>
                            <span class="rank-badge">
                                <c:choose>
                                    <c:when test="${status.index == 0}">🥇</c:when>
                                    <c:when test="${status.index == 1}">🥈</c:when>
                                    <c:when test="${status.index == 2}">🥉</c:when>
                                    <c:otherwise>${status.index + 1}</c:otherwise>
                                </c:choose>
                            </span>
                        </td>
                        <td><strong>${team.teamName}</strong></td>
                        <td><span class="points-badge">${team.totalPoints}</span></td>
                        <td>${team.wins}</td>
                        <td>${team.losses}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty topTeams}">
        <p>No teams yet. <a href="${pageContext.request.contextPath}/team?action=form">Create one</a></p>
    </c:if>

    <div class="navigation-section">
        <h2>Navigation</h2>
        <div class="nav-grid">
            <a href="${pageContext.request.contextPath}/team?action=list" class="nav-card">
                <h3>🏆 Teams</h3>
                <p>Manage teams and view details</p>
            </a>
            <a href="${pageContext.request.contextPath}/player?action=list" class="nav-card">
                <h3>👥 Players</h3>
                <p>Manage player profiles</p>
            </a>
            <a href="${pageContext.request.contextPath}/tournament?action=list" class="nav-card">
                <h3>🎮 Tournaments</h3>
                <p>Create and manage tournaments</p>
            </a>
            <a href="${pageContext.request.contextPath}/match?action=list" class="nav-card">
                <h3>⚔️ Matches</h3>
                <p>Schedule and track matches</p>
            </a>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp" />
