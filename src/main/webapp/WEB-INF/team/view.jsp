<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Team: ${team.teamName}</h1>

    <div class="team-details">
        <div class="detail-card">
            <p><strong>Total Points:</strong> ${team.totalPoints}</p>
            <p><strong>Wins:</strong> ${team.wins}</p>
            <p><strong>Losses:</strong> ${team.losses}</p>
            <p><strong>Description:</strong> ${team.description}</p>
        </div>
    </div>

    <h2>Players (${players.size()})</h2>
    <c:if test="${not empty players}">
        <table class="data-table">
            <thead>
                <tr>
                    <th>Player Name</th>
                    <th>Role</th>
                    <th>Skill Level</th>
                    <th>Frags</th>
                    <th>K/D Ratio</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${players}" var="player">
                    <tr>
                        <td>${player.playerName}</td>
                        <td>${player.role}</td>
                        <td>${player.skill}</td>
                        <td>${player.frags}</td>
                        <td>${player.kd}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty players}">
        <p>No players in this team yet.</p>
    </c:if>

    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/team?action=list" class="btn btn-secondary">Back to Teams</a>
        <a href="${pageContext.request.contextPath}/player?action=form" class="btn btn-success">Add Player</a>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
