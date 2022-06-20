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
        <div class="w3-row w3-panel w3-border-top w3-border-bottom w3-border-blue">
            <div class="w3-col m8 l9">
                <p><c:out value="${Turno.mascota.nombre}" /> Tiene Turno</p>
                <p>el dia <c:out value="${Turno.fechaTurno}" />  a las  <c:out value="${Turno.horaTurno}" /></p>
                <p>Con el medico: <c:out value="${Turno.medico.nombre}" /></p>

            </div>
            <div class="w3-col m4 l3">
                <p>aca poner un boton cancelar turno</p>
            </div>
        </div>
        <p></p>
    </c:forEach>

</main>

</body>
</html>
