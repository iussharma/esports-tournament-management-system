<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Matches Management</h1>

    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/match?action=schedule" class="btn btn-success">+ Schedule Match</a>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Back to Dashboard</a>
    </div>

    <table class="data-table">
        <thead>
            <tr>
                <th>Match ID</th>
                <th>Tournament</th>
                <th>Team 1</th>
                <th>Team 2</th>
                <th>Score</th>
                <th>Winner</th>
                <th>Status</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${matches}" var="match">
                <tr>
                    <td>${match.matchId}</td>
                    <td>Tournament ${match.tournamentId}</td>
                    <td>Team ${match.team1Id}</td>
                    <td>Team ${match.team2Id}</td>
                    <td>${match.team1Points} - ${match.team2Points}</td>
                    <td><c:if test="${match.winnerId != '0' && not empty match.winnerId}">Team ${match.winnerId}</c:if></td>
                    <td><span class="badge badge-${match.status}">${match.status}</span></td>
                    <td>${match.matchDate}</td>
                    <td>
                        <c:if test="${match.status == 'scheduled'}">
                            <a href="${pageContext.request.contextPath}/match?action=submitResult&id=${match.matchId}" class="btn btn-sm btn-success">Submit Result</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty matches}">
                <tr>
                    <td colspan="9" class="text-center">No matches found. <a href="${pageContext.request.contextPath}/match?action=schedule">Schedule one</a></td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="../common/footer.jsp" />
