<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1><c:choose><c:when test="${not empty player}">Edit Player</c:when><c:otherwise>Create New Player</c:otherwise></c:choose></h1>

    <form method="POST" action="${pageContext.request.contextPath}/player" class="form-container">
        <input type="hidden" name="action" value="${not empty player ? 'update' : 'create'}">
        <c:if test="${not empty player}">
            <input type="hidden" name="playerId" value="${player.playerId}">
        </c:if>

        <div class="form-group">
            <label for="playerName">Player Name *</label>
            <input type="text" id="playerName" name="playerName" required placeholder="Enter player name"
                   value="${player.playerName}">
        </div>

        <div class="form-group">
            <label for="role">Role *</label>
            <select id="role" name="role" required>
                <option value="">-- Select Role --</option>
                <option value="Fragger" ${player.role == 'Fragger' ? 'selected' : ''}>Fragger</option>
                <option value="Support" ${player.role == 'Support' ? 'selected' : ''}>Support</option>
                <option value="IGL" ${player.role == 'IGL' ? 'selected' : ''}>IGL (In-Game Leader)</option>
                <option value="Rifler" ${player.role == 'Rifler' ? 'selected' : ''}>Rifler</option>
                <option value="AWPer" ${player.role == 'AWPer' ? 'selected' : ''}>AWPer</option>
            </select>
        </div>

        <div class="form-group">
            <label for="teamId">Team *</label>
            <select id="teamId" name="teamId" required>
                <option value="">-- Select Team --</option>
                <c:forEach items="${teams}" var="team">
                    <option value="${team.teamId}" ${player.teamId == team.teamId ? 'selected' : ''}>${team.teamName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="skill">Skill Level *</label>
            <select id="skill" name="skill" required>
                <option value="">-- Select Level --</option>
                <option value="Beginner" ${player.skill == 'Beginner' ? 'selected' : ''}>Beginner</option>
                <option value="Intermediate" ${player.skill == 'Intermediate' ? 'selected' : ''}>Intermediate</option>
                <option value="Advanced" ${player.skill == 'Advanced' ? 'selected' : ''}>Advanced</option>
                <option value="Expert" ${player.skill == 'Expert' ? 'selected' : ''}>Expert</option>
                <option value="Pro" ${player.skill == 'Pro' ? 'selected' : ''}>Pro</option>
            </select>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <c:choose><c:when test="${not empty player}">Update Player</c:when><c:otherwise>Create Player</c:otherwise></c:choose>
            </button>
            <a href="${pageContext.request.contextPath}/player?action=list" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
