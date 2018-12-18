<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>CA AutoSys. Admin experience</title>
    <link href="<c:url value="../static/bootstrap/css/fancybox.min.css"/>" rel="stylesheet">
    <link href="<c:url value="../static/bootstrap/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="../static/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/bootstrap/js/fancybox.min.js"></script>
    <script src="https://www.w3schools.com/lib/w3.js"></script>

</head>
<body>

<jsp:include page="menu.jsp" flush="true"/>


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
            <label for="ava" class="col-sm-3 control-label">Avatar</label>

            <div id="ava" class="avatarWrap">
                <div class="avatarBtnGroup btn-group-justified" data-toggle="buttons">

                    <label class="btn avatarMethod active">
                        <div class="method ava1"></div>
                        <input type="radio" name="options" id="ava1" value="../static/users/icons/1.jpg" checked>
                    </label>

                    <label class="btn avatarMethod">
                        <div class="method ava2"></div>
                        <input type="radio" name="options" id="ava2" value="../static/users/icons/2.jpg">
                    </label>

                    <label class="btn avatarMethod">
                        <div class="method ava3"></div>
                        <input type="radio" name="options" id="ava3" value="../static/users/icons/3.jpg">
                    </label>

                    <label class="btn avatarMethod">
                        <div class="method ava4"></div>
                        <input type="radio" name="options" id="ava4" value="../static/users/icons/4.png">
                    </label>

                    <label class="btn avatarMethod">
                        <div class="method ava5"></div>
                        <input type="radio" name="options" id="ava5" value="../static/users/icons/5.jpg">
                    </label>
                    <label class="btn avatarMethod">
                        <div class="method ava6"></div>
                        <input type="radio" name="options" id="ava6" value="../static/users/icons/6.jpg">
                    </label>
                    <label class="btn avatarMethod">
                        <div class="method ava7"></div>
                        <input type="radio" name="options" id="ava7" value="../static/users/icons/7.png">
                    </label>
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
    </form> <!-- /form -->
</div> <!-- ./container -->


</body>
</html>
