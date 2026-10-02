<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Error</title></head>
<body>
<h1>Error</h1>
<p><c:out value="${message}"/></p>
<p><a href="${pageContext.request.contextPath}/tasks">Back to list</a></p>
</body>
</html>
