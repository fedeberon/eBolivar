<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp"/>
<html>
<header>

    <script type="text/javascript">

        $(document).ready(function () {
            $('#usuario').addClass('active')

        });

    </script>

</header>


<body>


<div style="margin-left: 80px; margin-right: 80px; padding-top:10px; background-color: white; ">
    <div class="page-header">
        <hr>
        <span class="titulo-descripcion" style="padding-left: 10px;">Nuevo Usuario</span>
    </div>



        <form:form action="save" modelAttribute="usuario" method="post">

            <p class="odd">
                <label for="username" class="campo">Username:</label>
                <form:input path="username" />
            </p>
            <br>
            <p class="odd">
                <label for="nombre" class="campo">Nombre:</label>
                <form:input path="nombre" />
            </p>
            <br>
            <p class="odd">
                <label for="apellido" class="campo">Apellido:</label>
                <form:input path="apellido" />
            </p>
            <br>
            <p class="odd">
                <label for="password" class="campo">Password:</label>
                <form:input path="password" />
            </p>
            <br>
            <button type="submit" class="btn btn-lg btn-primary btn-block">Crear</button>


        </form:form>

</div>

<jsp:include page="../bottom.jsp"/>


</body>
</html>

