<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Task Entry</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            max-width: 500px;
            margin: 20px 0;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
        .button-group {
            margin-top: 20px;
            display: flex;
            gap: 10px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        button:hover {
            background-color: #45a049;
        }
        .cancel-btn {
            background-color: #888;
            text-decoration: none;
            display: inline-block;
            padding: 10px 20px;
            border-radius: 4px;
            color: white;
        }
        .cancel-btn:hover {
            background-color: #666;
        }
    </style>
</head>
<body>

<h2>Create New Task</h2>

<div class="form-container">
    <form:form action="${pageContext.request.contextPath}/tasks/new" modelAttribute="task" method="POST">

        <div class="form-group">
            <label for="title">Title:</label>
            <form:input path="title" id="title" placeholder="Enter task title" />
            <form:errors path="title" cssClass="error" />
        </div>

        <div class="form-group">
            <label for="priority">Priority:</label>
            <form:select path="priority" id="priority">
                <form:option value="">-- Select Priority --</form:option>
                <form:option value="HIGH">High</form:option>
                <form:option value="MEDIUM">Medium</form:option>
                <form:option value="LOW">Low</form:option>
            </form:select>
            <form:errors path="priority" cssClass="error" />
        </div>

        <div class="form-group">
            <label for="completed">
                <form:checkbox path="completed" id="completed" />
                Mark as Completed
            </label>
        </div>

        <div class="button-group">
            <button type="submit">Save Task</button>
            <a href="${pageContext.request.contextPath}/tasks" class="cancel-btn">Cancel</a>
        </div>
    </form:form>
</div>

</body>
</html>
