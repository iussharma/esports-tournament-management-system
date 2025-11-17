<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="adminUsername" value="${sessionScope.adminUsername}" />
<jsp:include page="../common/header.jsp" />

<div class="content-section">
    <h1><c:choose><c:when test="${not empty team}">Edit Team</c:when><c:otherwise>Create New Team</c:otherwise></c:choose></h1>

    <form method="POST" action="${pageContext.request.contextPath}/team" class="form-container">
        <input type="hidden" name="action" value="${not empty team ? 'update' : 'create'}">
        <c:if test="${not empty team}">
            <input type="hidden" name="teamId" value="${team.teamId}">
        </c:if>

        <div class="form-group">
            <label for="teamName">Team Name *</label>
            <input type="text" id="teamName" name="teamName" required placeholder="Enter team name"
                   value="${team.teamName}">
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" rows="4" placeholder="Team description">${team.description}</textarea>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <c:choose><c:when test="${not empty team}">Update Team</c:when><c:otherwise>Create Team</c:otherwise></c:choose>
            </button>
            <a href="${pageContext.request.contextPath}/team?action=list" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
