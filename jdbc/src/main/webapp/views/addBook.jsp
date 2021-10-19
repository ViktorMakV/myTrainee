<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.10.2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add book</title>
</head>
<body>
<form method="post">
    <label>Author:
        <input type="text" name="author"><br />
    </label>

    <label>Book:
        <input type="text" name="book"><br />
    </label>
    <button type="submit">Add</button>
</form>
<br/>
<a>
    <%
        if (request.getAttribute("result") != null && (boolean)request.getAttribute("result")) {
            out.print("Added successful");
        }
    %>
</a>
<br/>
<button onclick="location.href='/'">Back to main</button>
</body>
</html>
