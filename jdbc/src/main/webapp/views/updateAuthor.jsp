<%@ page import="com.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.10.2021
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Update author</title>
</head>
<body>
<%
    String id = "";
    String name = "";
    if (request.getAttribute("author") != null) {
        Author author = (Author) request.getAttribute("author");
        id = String.valueOf(author.getId());
        name = author.getName();
    }
%>
<form method="post">
    <input type="hidden" name="id" value="<%=id%>"><br/>
    <label>Name:
        <input type="text" name="name" value="<%=name%>"><br/>
    </label>
    <button type="submit">Update</button>
</form>
</body>
</html>
