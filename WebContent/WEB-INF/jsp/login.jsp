<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
    <title>Login</title>

    <link rel="stylesheet" href="<c:url value='/css/login.css'/>" type="text/css"
          media="print, projection, screen"/>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" type="text/css"
          media="print, projection, screen"/>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap-theme.min.css'/>" type="text/css"
          media="print, projection, screen"/>
    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>


    <script type="text/javascript">

        $(document).ready(function () {

            document.getElementById("j_username").focus();

        });


    </script>

</head>

<body>


<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <!--             <h1 class="text-center login-title">Sign in to continue to Bootsnipp</h1> -->
            <div class="account-wall">
                <img style="height:105px; " src="<c:url value='/img/institucional/logo-institucional.png'/>"/>
                <div style="padding-top: 8px" align="right">
                    <font class="logoNombre1">Rentas</font><font class="logoNombre2"> Web</font></div>
                <hr class="separador">
                <img class="profile-img" src="<c:url value='/img/user.png'/>" alt="Ingreso">
                <form class="form-signin" action="<c:url value='/login'/>" method="post">
                    <input type="text" name="username" id="username" class="form-control" placeholder="Usuario"
                           required autofocus>
                    <input type="password" name="password" id="password" class="form-control" placeholder="Password"
                           required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
                    <label class="checkbox pull-left">

                        <div id="remember" class="checkbox">
                            <label>
                                <input type="checkbox" value="remember-me">Remember me
                            </label>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </label>
                </form>
            </div>
            <%--<a href="#" class="text-center new-account">Create an account </a>--%>
        </div>
    </div>
</div>


<!-- FOOTER
<div id="foot">

    <p>&copy; Copyright 2013 <a href="http://www.bolivar.gob.ar/">Municipalidad de Bolivar</a></p>
    <p><strong>Area Sistemas </strong> by <a href="#">Sistemas</a></p>

</div> <!-- end footer -->

</body>
</html>