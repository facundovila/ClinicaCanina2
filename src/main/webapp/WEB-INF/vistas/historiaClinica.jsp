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
            <p>medicamentos: ${historiaclinica.sintomas}</p>
            <p>tratamiento: ${historiaclinica.detalleTratamientos}</p>

        </div>



    </div>
</main>



</body>
</html>
