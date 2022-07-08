<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="partials/head.jsp"%>
</head>
<body>

<header>
    <%@ include file="partials/usuarioMenu.jsp"%>

</header>

<main>
    <div class="w3-col s9 w3-center">

        <form:form action="modificar-historia-clinica" method="POST"
                   modelAttribute="mascota">
            <p class=" w3-margin-top">
                <label for="id">Id</label>
                <form:input path="id" id="id" type="text" class="w3-input"
                            placeholder="" />
                <label for="nombre">Nombre</label>
                <form:input path="nombre" id="nombre" type="text" class="w3-input"
                            placeholder="nombre:" />
                <label for="sintomas">Sintomas</label>
                <form:input path="sintomas" id="sintomas" type="text" class="w3-input"
                            placeholder="Sintomas:" />
            </p>
            <p class=" w3-margin-top">
                <label for="detalleTratamientos">Detalle del tratamiento</label>
                <form:input path="detalleTratamientos" type="text" id="detalleTratamientos"
                            class="w3-input" placeholder="Tratamiento dado:" />
            </p>
            <p class=" w3-margin-top">
                <label for="edad">edad</label>
                <form:input path="edad" type="text"
                            id="edad" class="w3-input"
                            placeholder="edad:" />
            </p>
            <p class=" w3-margin-top">
                <label for="peso">peso</label>
                <form:input path="peso" type="text"
                            id="peso" class="w3-input"
                            placeholder="peso:" />

                <form:button type="submit" class="btn btn-primary mt-2">modificar</form:button>
            </p>
            <a href="login" class="w3-text-white">Login</a>
        </form:form>
    </div>
</main>
</body>
</html>
