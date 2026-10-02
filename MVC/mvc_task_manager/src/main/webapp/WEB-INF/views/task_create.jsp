<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>New Task Entry</title></head>
<body>

<h2>Create New Task</h2>
<form:form action="${pageContext.request.contextPath}/tasks/new" modelAttribute="task" method="POST">

    <label for="title">Title:</label>
    <form:input path="title" id="title" />
    <form:errors path="title" cssStyle="color:red;" /><br><br>

    <label for="priority">Priority:</label>
    <form:select path="priority" id="priority">
        <form:option value="HIGH">High</form:option>
        <form:option value="MEDIUM">Medium</form:option>
        <form:option value="LOW">Low</form:option>
    </form:select>
    <form:errors path="priority" cssStyle="color:red;" /><br><br>

    <%-- ADDED COMPLETED CHECKBOX --%>
    <label for="completed">Mark as Completed:</label>
    <form:checkbox path="completed" id="completed" /><br><br>

    <button type="submit">Save Task</button>
</form:form>
</body>
</html>