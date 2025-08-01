<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Add Candidate</title>
</head>
<body>
<h2>Add Candidate</h2>

<%--@elvariable id="candidate" type="Candidate"--%>
<form:form method="POST" action="AddCandidate" modelAttribute="candidate">
  Name: <form:input path="name"/><form:errors path="name" cssClass="error"/><br><br>
  Email: <form:input path="email"/><form:errors path="email" cssClass="error"/><br><br>
  Mobile: <form:input path="phone"/><form:errors path="phone" cssClass="error"/><br><br>
  Address: <form:input path="address"/><form:errors path="address" cssClass="error"/><br><br>

  <input type="submit" value="Add Candidate">
</form:form>
</body>
</html>
