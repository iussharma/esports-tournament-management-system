<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Teams Management</h1>

    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/team?action=form" class="btn btn-success">+ New Team</a>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Back to Dashboard</a>
    </div>

    <table class="data-table">
        <thead>
            <tr>
                <th>Team ID</th>
                <th>Team Name</th>
                <th>Description</th>
                <th>Total Points</th>
                <th>Wins</th>
                <th>Losses</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${teams}" var="team">
                <tr>
                    <td>${team.teamId}</td>
                    <td>${team.teamName}</td>
                    <td>${team.description}</td>
                    <td><strong>${team.totalPoints}</strong></td>
                    <td>${team.wins}</td>
                    <td>${team.losses}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/team?action=edit&id=${team.teamId}" class="btn btn-sm btn-primary">Edit</a>
                        <a href="${pageContext.request.contextPath}/team?action=view&id=${team.teamId}" class="btn btn-sm btn-info">View</a>
                        <form method="POST" action="${pageContext.request.contextPath}/team" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${team.teamId}">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Delete this team?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty teams}">
                <tr>
                    <td colspan="7" class="text-center">No teams found. <a href="${pageContext.request.contextPath}/team?action=form">Create one</a></td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="../common/footer.jsp" />
