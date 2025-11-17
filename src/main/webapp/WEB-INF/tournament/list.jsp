<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Tournaments Management</h1>

    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/tournament?action=form" class="btn btn-success">+ New Tournament</a>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Back to Dashboard</a>
    </div>

    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tournament Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Prize Pool</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tournaments}" var="tournament">
                <tr>
                    <td>${tournament.tournamentId}</td>
                    <td>${tournament.tournamentName}</td>
                    <td>${tournament.startDate}</td>
                    <td>${tournament.endDate}</td>
                    <td>${tournament.prizePool}</td>
                    <td><span class="badge badge-${tournament.status}">${tournament.status}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/tournament?action=edit&id=${tournament.tournamentId}" class="btn btn-sm btn-primary">Edit</a>
                        <form method="POST" action="${pageContext.request.contextPath}/tournament" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${tournament.tournamentId}">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Delete this tournament?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty tournaments}">
                <tr>
                    <td colspan="7" class="text-center">No tournaments found. <a href="${pageContext.request.contextPath}/tournament?action=form">Create one</a></td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="../common/footer.jsp" />
