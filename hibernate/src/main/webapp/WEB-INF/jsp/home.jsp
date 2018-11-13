<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>HOME PAGE</h3>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <td>
        <a href="user">List Users</a>
        </td>
    </tr>
    <tr>
        <td>
        <a href="tool">List Tool</a>
        </td>
    </tr>
</table>


<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>
