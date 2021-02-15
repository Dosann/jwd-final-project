<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<h2>Courses list</h2>
<%--<c:if test="${not empty requestScope.courses}">--%>
<%--    <h2>Columns</h2>--%>
<%--    <ul>--%>
<%--        <c:forEach var="course" items="${requestScope.courses}">--%>
<%--            <li>${course.name} description: ${course.description} starts on ${course.startDate}</li>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
<%--</c:if>--%>


<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #478eff">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/controller" class="nav-link">Go back</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center">List of Courses</h3>
        <hr>
        <div class="container text-left">

<%--            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Course</a>--%>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Start date</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="course" items="${requestScope.listCourse}">

                <tr>
                    <td>
                        <c:out value="${course.name}" />
                    </td>
                    <td>
                        <c:out value="${course.description}" />
                    </td>
                    <td>
                        <c:out value="${course.startDate}" />
                    </td>
<%--                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>--%>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>

</body>
</html>