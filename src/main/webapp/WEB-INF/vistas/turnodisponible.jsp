<jsp:include page="/partials/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>



<c:if test="${not empty noHayTunosDisponibles }">

    <h2>Lista de turnos</h2>
    ${msg}

</c:if>



<c:if test="${ empty noHayTunosDisponibles}">

    <h2>
        situacion de turnos:
    </h2>
    <h4>
            ${turnosDisponibles}
    </h4>
</c:if>

</body>
</html>