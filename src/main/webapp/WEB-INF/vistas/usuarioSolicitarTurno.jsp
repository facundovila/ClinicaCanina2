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

    <form:form action="irSoliciarTurnoPorFecha"  modelAttribute="datosSolicitarTurno" method="POST">
        <input type="date" id="fecha2" name="fecha2">
        <button class="w3-button w3-green w3-round w3-small w3-border w3-margin-top" type="submit" formaction="irSoliciarTurnoPorFecha">Consultar</button>
    </form:form>

    <c:if test="${not empty mensaje}">
        <div class="w3-panel w3-red w3-padding-16">${mensaje}</div>
    </c:if>

    <c:forEach var="Turno" items="${listaTurnosDisponibles}">
        <div class="w3-row w3-panel w3-border-top w3-border-bottom w3-border-blue">
            <div class="w3-col m8 l9">
                <p>Turno Nro: <c:out value="${Turno.id}"/></p>
                <p>el dia <c:out value="${Turno.fechaTurno.time.date}"></c:out>/<c:out value="${Turno.fechaTurno.time.month+1}" />  a las <c:out value="${Turno.fechaTurno.time.hours}"></c:out> :<c:out value="${Turno.fechaTurno.time.minutes}" /></p>
                <p>Con el medico: <c:out value="${Turno.medico.nombre}" /></p>
            </div>
            <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-blue">Solicitar Turno</button>

            <div id="id01" class="w3-modal">
                <div class="w3-modal-content">
                    <header class="w3-container w3-blue">
                        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                        <h2> Solicitar turno</h2>
                    </header>
                    <div class="w3-container">
                        <h3> El turno es el <c:out value="${Turno.fechaTurno.time.date}"></c:out>/<c:out value="${Turno.fechaTurno.time.month+1}"></c:out>
                            a las <c:out value="${Turno.fechaTurno.time.hours}"></c:out> :<c:out value="${Turno.fechaTurno.time.minutes}" />
                        </h3>

                        <c:if test="${not empty listaMascotas}">
                        <p>Seleccione Su Mascota Para el Turno</p>
                        <form:form action="tomarTurno/${Turno.id}/${Mascota.id}" method="POST">
                                <c:forEach var="Mascota" items="${listaMascotas}">
                                    <button class="w3-button w3-green w3-round w3-small w3-border w3-margin-top" type="submit" formaction="tomarTurno/${Turno.id}/${Mascota.id}">Turno para: ${Mascota.nombre} </button>
                                </c:forEach>
                        </form:form>
                        <button onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-red">Cancelar</button>
                        </c:if>

                        <c:if test="${not empty listaMascotas}">
                            <p>Seleccione Su Mascota Para el Turno</p>
                            <form:form action="tomarTurno/${Turno.id}/${Mascota.id}" method="POST">
                                <c:forEach var="Mascota" items="${listaMascotas}">
                                    <button class="w3-button w3-green w3-round w3-small w3-border w3-margin-top" type="submit" formaction="tomarTurno/${Turno.id}/${Mascota.id}">Turno para: ${Mascota.nombre}</button>
                                </c:forEach>
                            </form:form>
                            <button onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-red">Cancelar</button>
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