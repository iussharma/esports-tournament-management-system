<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1>Submit Match Result</h1>

    <form method="POST" action="${pageContext.request.contextPath}/match" class="form-container">
        <input type="hidden" name="action" value="updateResult">
        <input type="hidden" name="matchId" value="${match.matchId}">

        <div class="form-info">
            <p><strong>Match ID:</strong> ${match.matchId}</p>
            <p><strong>Team 1 ID:</strong> ${match.team1Id}</p>
            <p><strong>Team 2 ID:</strong> ${match.team2Id}</p>
        </div>

        <div class="form-group">
            <label for="team1Points">Team 1 Points *</label>
            <input type="number" id="team1Points" name="team1Points" required min="0" value="${match.team1Points}">
        </div>

        <div class="form-group">
            <label for="team2Points">Team 2 Points *</label>
            <input type="number" id="team2Points" name="team2Points" required min="0" value="${match.team2Points}">
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Submit Result</button>
            <a href="${pageContext.request.contextPath}/match?action=list" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
