<%--
  Created by IntelliJ IDEA.
  User: shangjie
  Date: 17-5-10
  Time: 上午8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
<div class="right_col" role="main">
    <div class="x_content text-center" style="padding-top: 50px;">
        <h1 class="error-number" style="font-size: 60px;">ERROR</h1>
        <h2><i class="fa fa-warning text-red margin-right-10px"></i>很抱歉，系统出现异常！</h2>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
        ${exception}
    </div>
</div>
</body>
</html>
