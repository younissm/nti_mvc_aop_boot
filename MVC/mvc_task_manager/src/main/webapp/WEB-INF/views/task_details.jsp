<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Task Details</title>
</head>
<body>

<h2>Task Details</h2>

<p><strong>Task ID:</strong> ${task.id}</p>
<p><strong>Title:</strong> ${task.title}</p>
<p><strong>Priority:</strong> ${task.priority}</p>

<%-- ADDED STATUS DISPLAY FIELD --%>
<p>
    <strong>Status:</strong>
    <c:choose>
        <c:when test="${task.completed}">
            <span style="color: green; font-weight: bold;">Completed</span>
        </c:when>
        <c:otherwise>
            <span style="color: orange; font-weight: bold;">Pending</span>
        </c:otherwise>
    </c:choose>
</p>

<hr>
<p>
    <a href="${pageContext.request.contextPath}/tasks">Back to Task List</a>
</p>

</body>
</html>