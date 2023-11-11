<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View and Add Parameters</title>
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
    <h1>View and Add Parameters</h1>

    <!-- Display parameters for the selected ProductGroup -->
    <h2>Parameters for ${selectedProductGroup.name}</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Measure</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selectedParameters}" var="parameter">
            <tr>
                <td>${parameter.name}</td>
                <td>${parameter.measure}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Form to add parameters to the selected ProductGroup -->
    <h2>Add Parameter to ProductGroup</h2>
    <form method="post">
        <div class="form-group">
            <label for="parameterId">Parameter</label>
            <select id="parameterId" name="parameterId" class="form-control" required>
                <c:forEach items="${parameters}" var="parameter">
                    <option value="${parameter.id}">${parameter.name}</option>
                </c:forEach>
            </select>
        </div>
        <input type="hidden" name="productGroupId" value="${selectedProductGroup.id}">
        <button type="submit" class="btn btn-primary">Add Parameter to ProductGroup</button>
    </form>
</div>
<!-- Include Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>