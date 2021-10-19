<%@ page import="com.model.Storage" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.10.2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>List of books</title>
</head>
<body>
<ul>
    <%
        List<Storage> storageList = (List<Storage>) request.getAttribute("storageList");

        if (storageList != null && !storageList.isEmpty()) {
            for (Storage s : storageList) {
                out.println("<li>" + s + "</li>");
            }
        }
    %>
</ul>
<br/>
<button onclick="location.href='/'">Back to main</button>
</body>
</html>
