<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Players Management</h1>

    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/player?action=form" class="btn btn-success">+ New Player</a>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Back to Dashboard</a>
    </div>

    <table class="data-table">
        <thead>
            <tr>
                <th>Player ID</th>
                <th>Player Name</th>
                <th>Role</th>
                <th>Team</th>
                <th>Skill Level</th>
                <th>Frags</th>
                <th>K/D Ratio</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td>${player.playerId}</td>
                    <td>${player.playerName}</td>
                    <td>${player.role}</td>
                    <td>Team ${player.teamId}</td>
                    <td>${player.skill}</td>
                    <td>${player.frags}</td>
                    <td>${player.kd}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/player?action=edit&id=${player.playerId}" class="btn btn-sm btn-primary">Edit</a>
                        <form method="POST" action="${pageContext.request.contextPath}/player" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${player.playerId}">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Delete this player?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty players}">
                <tr>
                    <td colspan="8" class="text-center">No players found. <a href="${pageContext.request.contextPath}/player?action=form">Create one</a></td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="../common/footer.jsp" />
