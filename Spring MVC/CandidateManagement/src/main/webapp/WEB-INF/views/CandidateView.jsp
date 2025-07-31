<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Requested Candidate</title>
</head>
<body>
    <h1>Your Requested Candidate details are:</h1>
<%=request.getAttribute("candidate")%>
</body>
</html>
