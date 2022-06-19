<jsp:include page="/partials/head.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/estilos.css">

<body>

<main class="fondo">
    <div class="container">

            <h2 class="mb-3">Historia Clinica</h2>

        <div>
            <p>nombre: ${historiaclinica.nombre}</p>
            <p>peso: ${historiaclinica.peso}</p>
            <p>edad: ${historiaclinica.edad}</p>
            <p>sintomas: ${historiaclinica.sintomas}</p>
            <p>tratamiento dado: ${historiaclinica.detalleTratamientos}</p>

        </div>



    </div>
    <br>
    <br>
    <br>
    <br>
</main>

<footer>
    <button class="btn  btn-primary btn-lg active link" Type="Submit"/>
    <a  href="cerrar-sesion" class="link-dark">Cerrar sesion</a></button>
</footer>
</body>
</html>
