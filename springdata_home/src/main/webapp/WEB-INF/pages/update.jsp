<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Update</title>
    <</head>


<body>

<jsp:include page="_menu.jsp" flush="true"/>

<div align="center">
    <h1>Your login is: ${login}, your roles are:</h1>
    <c:forEach var="s" items="${roles}">
        <h3><c:out value="${s}" /></h3>
    </c:forEach>

    <c:if test="${login == 'admin'}">
        <b><p style="background-color: #dfad64">You can <a href="/admin">add, update or delete</a> content in this site</p></b>
    </c:if>

    <c:url value="/update" var="updateUrl" />
    <form action="${updateUrl}" method="POST">
        E-mail:<br/><input type="text" name="email" value="${email}" /><br/>
        Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
        <input type="submit" value="Update" />
    </form>

    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>
</body>
</html>