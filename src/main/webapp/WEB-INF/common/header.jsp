<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gaming.css">
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="site-header container">
    <div class="brand">
        <div class="logo">OD</div>
        <div>
            <div class="title">ODDSOCEAN</div>
            <div class="muted" style="font-size:0.85rem">ESPORTS</div>
        </div>
    </div>
    <div class="nav">
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/team">Teams</a>
        <a href="${pageContext.request.contextPath}/player">Players</a>
        <a href="${pageContext.request.contextPath}/tournament">Tournaments</a>
        <a href="${pageContext.request.contextPath}/match">Matches</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>
    <div class="page-container">
