<%@ page import="java.util.List" %>
<%@ page import="com.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.10.2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>List of authors</title>
</head>
<body>
<ul>
    <%
        List<Author> authors = (List<Author>) request.getAttribute("authors");

        if (authors != null && !authors.isEmpty()) {
            for (Author a : authors) {
                out.println("<li>" + a + "</li>");
            }
        }
    %>
</ul>
<br/>
<button onclick="location.href='/'">Back to main</button>
</body>
</html>
