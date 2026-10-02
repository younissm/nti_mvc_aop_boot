<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Task Manager</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/tasks/new">Add New Task</a>

<hr>

<h2>Filter Tasks by Priority</h2>
<form action="${pageContext.request.contextPath}/tasks/search" method="GET">
    <label for="priority">Select Priority:</label>
    <select name="priority" id="priority">
        <option value="HIGH" <c:if test="${selectedPriority == 'HIGH'}">selected</c:if>>High</option>
        <option value="MEDIUM" <c:if test="${selectedPriority == 'MEDIUM'}">selected</c:if>>Medium</option>
        <option value="LOW" <c:if test="${selectedPriority == 'LOW'}">selected</c:if>>Low</option>
    </select>
    <button type="submit">Search</button>
    <a href="${pageContext.request.contextPath}/tasks">View All</a>
</form>

<hr>

<h2>
    <c:choose>
        <c:when test="${not empty selectedPriority}">
            Tasks with ${selectedPriority} Priority
        </c:when>
        <c:otherwise>
            All Tasks
        </c:otherwise>
    </c:choose>
</h2>

<table border="1" cellpadding="5">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Priority</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>
                <c:choose>
                    <c:when test="${task.priority == 'HIGH'}">
                        <span style="color: red; font-weight: bold;">High</span>
                    </c:when>
                    <c:when test="${task.priority == 'MEDIUM'}">
                        <span style="color: blue; font-weight: bold;">Medium</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: gray;">Low</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${task.completed}">
                        <span style="color: green; font-weight: bold;">Completed</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: orange; font-weight: bold;">Pending</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/tasks/${task.id}">View Details</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty tasks}">
        <tr>
            <td colspan="5">No tasks found.</td>
        </tr>
    </c:if>
    </tbody>
</table>

</body>
</html>
