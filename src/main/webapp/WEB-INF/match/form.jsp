<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Schedule New Match</h1>

    <form method="POST" action="${pageContext.request.contextPath}/match" class="form-container">
        <input type="hidden" name="action" value="create">

        <div class="form-group">
            <label for="tournamentId">Tournament *</label>
            <select id="tournamentId" name="tournamentId" required>
                <option value="">-- Select Tournament --</option>
                <c:forEach items="${tournaments}" var="tournament">
                    <option value="${tournament.tournamentId}">${tournament.tournamentName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="team1Id">Team 1 *</label>
            <input type="number" id="team1Id" name="team1Id" required placeholder="Enter Team 1 ID">
        </div>

        <div class="form-group">
            <label for="team2Id">Team 2 *</label>
            <input type="number" id="team2Id" name="team2Id" required placeholder="Enter Team 2 ID">
        </div>

        <div class="form-group">
            <label for="matchDate">Match Date & Time *</label>
            <input type="datetime-local" id="matchDate" name="matchDate" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Schedule Match</button>
            <a href="${pageContext.request.contextPath}/match?action=list" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
