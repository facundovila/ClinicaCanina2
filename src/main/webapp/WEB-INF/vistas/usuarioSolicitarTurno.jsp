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

    <form:form action="usuarioSolicitarTurno"  modelAttribute="datosSolicitarTurno" method="POST">
        <input type="date" id="fecha" name="fecha">
        <input type="submit">
    </form:form>





</main>

</body>
</html>
