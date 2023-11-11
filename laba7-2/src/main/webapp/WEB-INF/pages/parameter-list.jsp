<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Parameter List</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/parameter">Parameters</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/product-group">Parameters group</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="container">
    <h1>Parameter List</h1>

    <!-- Display a table of parameters -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Measure</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${parameters}" var="parameter">
            <tr>
                <td>${parameter.name}</td>
                <td>${parameter.measure}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Form to add a new parameter -->
    <h2>Add New Parameter</h2>
    <form method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="measure">Measure</label>
            <input type="text" id="measure" name="measure" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Parameter</button>
    </form>
</div>
<!-- Include Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>