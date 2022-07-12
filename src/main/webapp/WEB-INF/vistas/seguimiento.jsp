<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <%@ include file="partials/head.jsp"%>
    <link rel="stylesheet" href="../css/estilos.css">
    <link rel="stylesheet" href="css/styleAmbulancia.css">
</head>

<body>

<main>

 <c:if test="${not empty DatosNavegacion}">
        <div id="now">
            <span> Hora actual : ${DatosNavegacion.get(0)}</span>
             <br>
              <br>
            <span> Hora estimada de Llegada : ${DatosNavegacion.get(1)}</span>
             <br>
              <br>
            <span> Tiempo restante de recorrido : ${DatosNavegacion.get(2)}</span>
             <br>
              <br>
           
        </div>
 </c:if>
 </main>

</body>
</html>