<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<body>


<c:if test="${not empty AmbulanciaDisponible}">
    <h2>Usted podra reservar la siguiente ambulancia</h2>
    ${AmbulanciaDisponible}

</c:if>


<h2>Sin ambulancias disponibles por el momento</h2>

<h4>
    ${SinAmbulancias}
</h4>


</body>
</html>