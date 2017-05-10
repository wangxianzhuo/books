<%--
  Created by IntelliJ IDEA.
  User: shangjie
  Date: 17-5-10
  Time: 下午3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书列表</title>
</head>
<body>
<form action="/book" method="post">
    name: <input name="name">
    isbn: <input name="isbn">
    publisher: <input name="publisher">
    authors: <input name="authors">
    <button type="submit">submit</button>
</form>

<hr>

<table>
    <thead>
    <tr>
        <th>name</th>
        <th>isbn</th>
        <th>publisher</th>
        <th>authors</th>
    </tr>
    </thead>
    <c:if test="${not empty books}">
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.name}</td>
                <td>${book.isbn}</td>
                <td>${book.publisher}</td>
                <td>${book.authors}</td>
            </tr>
        </c:forEach>
        </tbody>
    </c:if>
</table>
<script type="application/javascript">
    console.log(${books})
</script>
</body>
</html>
