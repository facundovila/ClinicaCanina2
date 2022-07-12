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
                   modelAttribute="visita" >
            <p class=" w3-margin-top">
                <form:input path="mascotaAsignada.id" id="sintomas" type="hidden" class="w3-input" value="${mascota.id}"
                            placeholder=""  />
            </p>
            <p class=" w3-margin-top">
                <label for="detalleTratamientos">edad Actual</label>
                <form:input path="edad" type="text" id="detalleTratamientos"
                            class="w3-input" placeholder="Ingrese la edad" />
            </p>
            <p class=" w3-margin-top">
                <label for="detalleTratamientos">Peso Actual</label>
                <form:input path="peso" type="text" id="detalleTratamientos"
                            class="w3-input" placeholder="Ingrese el peso" />
            </p>
            <p class=" w3-margin-top">
                <label for="sintomas">Sintomas</label>
                <form:input path="sintomas" id="sintomas" type="text" class="w3-input"
                            placeholder="Sintomas:" />
            </p>
            <p class=" w3-margin-top">
                <label for="detalleTratamientos">Detalle del tratamiento</label>
                <form:input path="tratamiento" type="text" id="detalleTratamientos"
                            class="w3-input" placeholder="Tratamiento dado:" />
            </p>


                <form:button type="submit" class="btn btn-primary mt-2">modificar</form:button>
            </p>

        </form:form>


    </div>
</main>
</body>
</html>
