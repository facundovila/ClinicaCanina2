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
                <p><h2><c:out value="${Turno.mascota.nombre}" /> Tiene Turno</h2></p>
                <p><h2>el dia  <c:out value="${Turno.fechaTurno.time.date}"></c:out>/<c:out value="${Turno.fechaTurno.time.month+1}"></c:out> a las <c:out value="${Turno.fechaTurno.time.hours+3}"></c:out>:<c:out value="${Turno.fechaTurno.time.minutes}" />hs.</h2></p>
                <p><h2>Con el medico: <c:out value="${Turno.medico.nombre}" /></h2></p>
            </div>

                <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-blue">Cancelar Turno</button>

                <div id="id01" class="w3-modal">
                    <div class="w3-modal-content">
                        <header class="w3-container w3-teal">
                         <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                            <h2> Desea Cancelar Turno?</h2>
                        </header>
                        <div class="w3-container">
                            <p></p>
                            <button onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-green">Mantenerlo</button>
                            <form:form action="cancelarTurno/${Turno.id}" method="POST">
                                <button class="w3-button w3-red w3-round w3-small w3-border w3-margin-top"
                                        type="submit">Cancelar Turno</button>
                            </form:form>
                            <c:if test="${not empty mensaje}">
                                <div class="w3-panel w3-blue w3-padding-16">${mensaje}</div>
                            </c:if>
                        </div>
                    </div>
                </div>


            </div>
        <p></p>
    </c:forEach>

</main>

</body>
</html>
