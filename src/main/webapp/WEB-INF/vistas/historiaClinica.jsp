<%--
  Created by IntelliJ IDEA.
  User: facun
  Date: 7/6/2022
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/partials/header.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<main>
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
</main>


<footer>
    <button class="btn  btn-primary btn-lg active link" Type="Submit"/>
    <a  href="cerrar-sesion">Cerrar sesion</a></button>
</footer>
</body>
</html>
