<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session = "false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Servlet : Filters</title>
</head>
<body>
<h1>Servlet : Filters</h1>
Invoke the <a href="${pageContext.request.contextPath}/MyAsyncServlet"/>Async Servlet</a>.
<div>Test request dispatcher and forward to WEB-INF folder  <a href="${pageContext.request.contextPath}/dispatcher">Dispacther</a>  </div>
<div>Try Login  <a href="${pageContext.request.contextPath}/login-test.html">Login</a>  </div>
</body>
</html>