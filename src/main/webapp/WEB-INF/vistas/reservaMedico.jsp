<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="partials/head.jsp"%>
</head>
<body>
<h2>El medico reservado es: </h2>
<h4>Dni : ${medicoReservado.dni} </h4>
<h4>Nombre medico : ${medicoReservado.getNombre()} </h4>
<h4>Horario entrada : ${medicoReservado.getHorarioEntrada()} </h4>
<h4>Horario salida : ${medicoReservado.getHorarioSalida()} </h4>
<h4>Disponibilidad : ${medicoReservado.getDisponibilidad()} </h4>
</body>
</html>