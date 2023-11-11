<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
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
    <h1>Update Product</h1>

    <form method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" class="form-control" value="${product.name}" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" required>${product.description}</textarea>
        </div>
        <div class="form-group">
            <label for="productGroupId">Product Group</label>
            <select id="productGroupId" name="productGroupId" class="form-control" required>
                <c:forEach items="${productGroups}" var="group">
                    <c:choose>
                        <c:when test="${group.id == product.productGroupId}">
                            <option value="${group.id}" selected>${group.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${group.id}">${group.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update Product</button>
    </form>
    <!-- Parameter Values Section -->
    <div class="parameter-values">
        <h2>Parameter Values</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Parameter ID</th>
                <th>Value</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${parameterValues}" var="paramValue">
                <tr>
                    <td>${paramValue.name}</td>
                    <td>${paramValue.value}</td>
                    <td>${paramValue.measure}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form method="post" action="${pageContext.request.contextPath}/create-value">
            <div class="form-group">
                <label for="parameterId">Parameter</label>
                <select id="parameterId" name="parameterId" class="form-control" required>
                    <c:forEach items="${notUsedParameters}" var="parameter">
                        <option value="${parameter.id}">${parameter.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="name2">Value</label>
                <input type="text" id="name2" name="value" class="form-control" required>
            </div>
            <input type="hidden" name="productId" value="${product.id}">
            <button type="submit" class="btn btn-primary">New Parameter</button>
        </form>
    </div>
</div>
<!-- Include Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>