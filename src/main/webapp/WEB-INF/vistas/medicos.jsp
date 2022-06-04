<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<body>



<c:if test="${empty nohaymedicos}">

    <h2>Lista de Medicos</h2>
         ${medico}

</c:if>



<c:if test="${not empty nohaymedicos}">

<h2>
    situacion de medico:
</h2>
<h4>
     ${nohaymedicos}
</h4>
</c:if>

</body>
</html>