<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1><c:choose><c:when test="${not empty tournament}">Edit Tournament</c:when><c:otherwise>Create New Tournament</c:otherwise></c:choose></h1>

    <form method="POST" action="${pageContext.request.contextPath}/tournament" class="form-container">
        <input type="hidden" name="action" value="${not empty tournament ? 'update' : 'create'}">
        <c:if test="${not empty tournament}">
            <input type="hidden" name="tournamentId" value="${tournament.tournamentId}">
        </c:if>

        <div class="form-group">
            <label for="tournamentName">Tournament Name *</label>
            <input type="text" id="tournamentName" name="tournamentName" required placeholder="Enter tournament name"
                   value="${tournament.tournamentName}">
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" rows="4" placeholder="Tournament description">${tournament.description}</textarea>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date *</label>
            <input type="date" id="startDate" name="startDate" required value="${tournament.startDate}">
        </div>

        <div class="form-group">
            <label for="endDate">End Date *</label>
            <input type="date" id="endDate" name="endDate" required value="${tournament.endDate}">
        </div>

        <div class="form-group">
            <label for="prizePool">Prize Pool</label>
            <input type="text" id="prizePool" name="prizePool" placeholder="e.g., $50,000" value="${tournament.prizePool}">
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <c:choose><c:when test="${not empty tournament}">Update Tournament</c:when><c:otherwise>Create Tournament</c:otherwise></c:choose>
            </button>
            <a href="${pageContext.request.contextPath}/tournament?action=list" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
