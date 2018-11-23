<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Id</th>
        <th>Name</th>


        <th>Delete</th>
    </tr>
    <c:forEach items="${skillList}" var="skill" >

        <tr>
            <td>${skill.id}</td>
            <td>
                <form action="skill" method="POST">
                    <input name="id" id="id" type="hidden" value=${skill.id} />
                    <input name="name" id="name" type="text" value=${skill.name} />
                    <input type="submit" value="Edit" name="edit" />
                </form>
            </td>
            <td>
                <form action="skill" method="POST">
                    <input name="id" type="hidden" value=${skill.id} />
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>





<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
