<%@ page import="com.service.ConnectionPool" %>
<html>
<body>
<h2>Hello, Meatbag!</h2>
<%
    //Invoke DB
    new ConnectionPool();
%>
<div>
    <div>
        <button onclick="location.href='/listAuthors'">List authors</button>
        <button onclick="location.href='/addBook'">Add book</button>
    </div>
</div>
<br/>
<form method="get" action="${pageContext.request.contextPath}/listBooks">
    <label>Author name:</label>
    <input type="text" name="authorName">
    <button type="submit">List books of author</button>
</form>
<br/>
<form method="post" action="${pageContext.request.contextPath}/deleteAuthor">
    <label>Author name:</label>
    <input type="text" name="authorName">
    <button type="submit">Delete author</button>
</form>
<br/>
<form method="get" action="${pageContext.request.contextPath}/updateAuthor">
    <label for="authorName">Author name:</label>
    <input type="text" name="authorName" id="authorName">
    <button type="submit">Update author</button>
</form>
</body>
</html>
