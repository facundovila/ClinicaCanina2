<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="partials/head.jsp"%>
</head>
<body>
    <%@ include file="partials/usuarioMenu.jsp"%>
<main>
    <c:forEach var="Turno" items="${listaTurnosUsuario}">
        <tr>
            <td class="w3-center"><c:out value="${Turno.id}" /></td>
            <td class="w3-center"><c:out value="${Turno.fecha}" /></td>
            <td class="w3-center"><c:out value="${Turno.estado}" /></td>
        </tr>
    </c:forEach>


</main>


</body>
</html>
