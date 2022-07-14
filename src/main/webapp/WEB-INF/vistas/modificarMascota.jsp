<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="partials/head.jsp"%>
</head>
<body  class="fondo2">

<header>
    <%@ include file="partials/usuarioMenu.jsp"%>

</header>

<main >
    <br>
    <br>

    <div class="row justify-content-center aling-items-center">
        <div class="card text-center"  style="max-width: 50rem;">
            <div class="card-header"><h4>Nueva visita medica</h4></div>
            <div class="card-body text-center">

                <form:form action="modificar-historia-clinica" method="POST" modelAttribute="visita" >
                    <p class=" w3-margin-top">
                        <form:input path="mascotaAsignada.id" id="sintomas" type="hidden" class="form-control" value="${mascota.id}"
                                    placeholder=""  />
                    </p>
                    <div class="form-row">
                        <div class="form-group ">
                            <label for="edad">edad Actual</label>
                            <form:input path="edad" type="number" id="edad"
                                        class="form-control" placeholder="Ingrese la edad" />
                        </div>
                        <div class="form-group ">
                            <label for="peso">Peso Actual</label>
                            <form:input path="peso" type="number" step="0.01" id="peso"
                                        class="form-control" placeholder="Ingrese el peso" />
                        </div>
                    </div>

                    <div class="form-group " >
                        <label for="sintomas">Sintomas</label>
                        <form:input path="sintomas" id="sintomas" type="text" class="form-control"
                                    placeholder="Sintomas:" />
                    </div>
                    <div class="form-group ">
                        <label for="detalleTratamientos">Detalle del tratamiento</label>
                        <form:textarea path="tratamiento"  id="detalleTratamientos" cols="60" rows="10" class="form-control"></form:textarea>
                    </div>


                    <form:button type="submit" class="btn btn-primary mt-2">modificar</form:button>
                    </p>

                </form:form>
            </div>
        </div>



    </div>
</main>
</body>
</html>
