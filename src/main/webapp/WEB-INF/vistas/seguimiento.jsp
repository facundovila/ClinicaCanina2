<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <%@ include file="partials/head.jsp"%>
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="stylesheet" href="css/seguimiento.css">
</head>

<body>

<main>

 <c:if test="${not empty DatosNavegacion}">
        <div id="now">
            <span> Usted ha solicitado una ambulancia el dia y horario siguiente : ${DatosNavegacion.get(0)}</span>
             <br>
              <br>
            <span> El tiempo estimado de viaje hasta el domicilio indicado es de : ${DatosNavegacion.get(1)}</span>
             <br>
              <br>
            <span> La hora estimada de arrivo es : ${DatosNavegacion.get(2)}</span>
             <br>
              <br>
              <span> Restan : ${DatosNavegacion.get(3)} minutos para que arrive la ambulancia</span>
             <br>
              <br>
           
        </div>
 </c:if>
 <c:if test="${not empty Llegada}">
        <div id="now">
            <span> ${Llegada}</span>
             <br>
            
           
        </div>
 </c:if>
 </main>

</body>
</html>