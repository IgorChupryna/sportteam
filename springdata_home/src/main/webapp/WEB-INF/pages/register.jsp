<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Register form</title>

</head>
<body>

<jsp:include page="_menu.jsp" flush="true"/>


<div class="container">

    <c:url value="/newuser" var="regUrl"/>

    <form class="form-horizontal" action="${regUrl}" role="form1" method="POST">

        <h2>Registration Form</h2>
        <div class="form-group">
            <label for="username" class="col-sm-3 control-label">Username</label>
            <div class="col-sm-9">
                <input type="text" id="username" name="login" placeholder="Username" class="form-control" autofocus>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-3 control-label">Password</label>
            <div class="col-sm-9">
                <input type="password" id="password" name="password" placeholder="Password" class="form-control">
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-9">
                <input type="email" id="email" name="email" placeholder="Email" class="form-control">
            </div>
        </div>


        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Phone</label>
            <div class="col-sm-9">
                <input type="phone" id="phone" name="phone" placeholder="Phone" class="form-control">
            </div>
        </div>



        <div class="form-group">
            <div class="col-sm-5 col-sm-offset-3">
                <button type="submit" class="btn btn-primary btn-block">Register</button>
            </div>
        </div>


        <c:if test="${exists ne null}">
            <p>User already exists!</p>
        </c:if>
    </form>
</div> <!-- ./container -->


</body>
</html>
