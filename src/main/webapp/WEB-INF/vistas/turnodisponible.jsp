<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="partials/head.jsp"%>
</head>
<body>



<c:if test="${not empty msg }">

    <h2>Lista de turnos</h2>
    ${msg}

</c:if>



<c:if test="${ empty msg}">

    <h2>
        situacion de turnos:
    </h2>
    <h4>
            ${vacia}
    </h4>
</c:if>

</body>
</html>