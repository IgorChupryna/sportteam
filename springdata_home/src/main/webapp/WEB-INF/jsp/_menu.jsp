<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<div style='float: right; background: #f6fdde;padding: 10px; margin-right: 10px; '>
    <div class="form-inline" align="right">
        <c:url value="/j_spring_security_check" var="loginUrl"/>
        <c:if test="${login eq null}">
            <form action="${loginUrl}" method="POST">
                <input id="login-username" type="text" class="form-control" name="j_login" value="" placeholder="username">
                <input id="login-password" type="password" class="form-control" name="j_password" placeholder="password">
                <input class="btn btn-default" type="submit" value="Sign in"/>

                <p><a href="/register">Register new user</a></p>

                <c:if test="${param.error ne null}">
                    <p>Wrong login or password!</p>
                </c:if>

                <c:if test="${param.logout ne null}">
                    <p>Chao!</p>
                </c:if>
            </form>
        </c:if>
    </div>
</div>
<div style="padding: 5px;">


    <a href="${pageContext.request.contextPath}/home">Home</a>
    |
    <a href="${pageContext.request.contextPath}/user">Users List</a>
    |
    <a href="${pageContext.request.contextPath}/project">Projects List</a>
    |
    <a href="${pageContext.request.contextPath}/other">Other DB Lists</a>

    <head>
        <title>CA AutoSys. Admin experience</title>
        <link href="<c:url value="/static/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    </head>


</div>

</body>
</html>
