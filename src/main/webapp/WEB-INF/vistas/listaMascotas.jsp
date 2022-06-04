<jsp:include page="/partials/header.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mascotas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<header>

</header>
<main>
    <div class="container">
        <c:if test="${empty sinMascotas}">
            <h2 class="mb-3">Lista De Mascotas</h2>
            <div class="col-6">


                <table class="table user-list">
                    <thead>
                    <tr>
                        <th class="d-none"></th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Peso</th>
                        <th class="text-center">Edad</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listarmascotas}" var="mascota">
                        <tr>
                            <td class="d-none">${mascota.id}</td>
                            <td class="text-center">${mascota.nombre}</td>
                            <td class="text-center">${mascota.peso}</td>
                            <td class="text-center">${mascota.edad}</td>

                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

                <h2>${sinMascotas}</h2>



    </div>

</main>

</body>
</html>
