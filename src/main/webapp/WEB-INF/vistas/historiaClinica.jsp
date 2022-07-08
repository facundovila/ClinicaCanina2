<%@ include file="partials/head.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/estilos.css">

<body>

<main class="">
    <div class="container">

            <h2 class="mb-3">Historia Clinica</h2>

        <div>
            <p>nombre: ${mascota.nombre}</p>
            <p>peso: ${mascota.peso}</p>
            <p>edad: ${mascota.edad}</p>
            <p>sintomas: ${mascota.sintomas}</p>
            <p>tratamiento dado: ${mascota.detalleTratamientos}</p>


        </div>

        <div>
            <p>
                <a href="modificar-mascota?idMascota=${mascota.id}" class="w3-text-blue">Modificar Historia Clinica</a>
            </p>
        </div>

    </div>
    <br>
    <br>
    <br>
    <br>
</main>

<footer>
    <div class="link">
        <button class="btn  btn-primary btn-lg active link" Type="Submit"/>
        <a  href="cerrar-sesion" class="link-dark">Cerrar sesion</a></button>
    </div>
</footer>
</body>
</html>
