<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<link rel="stylesheet" href="../../css/estilos.css">
<link rel="stylesheet" href="css/styleAmbulancia.css">

<body>

<main>
    <c:if test="${not empty AmbulanciaDisponible}">
   
    <h3 class="form-signin-heading">Reservar Ambulancia</h3>
   
     <form:form action="reservar-ambulancia" method="POST" modelAttribute="datosReservaAmbulancia" id="formulario">
			    	
					<hr class="colorgraph"><br>
					
                   
					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto datosReservaAmbulancia se guardan los datos ingresados--%>
					<div class = "inputs">
					<form:label path="direccionCalle">Ingrese una Calle : </form:label>
					<br>
					<form:input path="direccionCalle" id="direccionCalle" type="text" class="form-control" minlength="2" maxlength="30"/>
					</div>
					<div class = "inputs">
					<form:label path="direccionNumero">Ingrese un Numero de Calle : </form:label>
					<br>
					<form:input path="direccionNumero" id="direccionNumero" type="number" class="form-control" />
					</div>
					<div class = "inputs">
					<form:label path="telefono">Ingrese Numero de Telefono : </form:label>
					<br>
					<form:input path="telefono" id="telefono" type="text" class="form-control" minlength="8" maxlength="10"/>
					</div>
					<div class = "inputs">
					<form:label path="motivo">Explique brevemente el motivo de su urgencia : </form:label>
					<br>
					<form:input path="motivo" id="motivo" type="text" class="form-control" minlength="4" maxlength="50"/>
					</div>
					
					<div id="patente" style = "display : none">
					<form:label path="patente">Reservara la siguiente ambulancia con patente : ${AmbulanciaDisponible.get(0).getPatente()}</form:label>
					<br>
					<form:input path="patente" type="text"  value="${AmbulanciaDisponible.get(0).getPatente()}" class="form-control"/>  
					</div>
					<div id = "boton">
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Reservar</button>
					</div>
					<!--<c:if test="${not empty ErrorDatos}">
					 <span> ${ErrorDatos} </span>
					</c:if>-->
				</form:form>

</c:if>
<c:if test="${not empty ErrorDatos}">
					 <span> ${ErrorDatos} </span>
					</c:if>
<c:if test="${not empty SinAmbulancias}">
  <div id = "noAmbulancias">				
    <h2>
    ${SinAmbulancias}
   </h2>
  </div>	
</c:if>

    <c:if test="${not empty ReservaRealizada}">
    <div id="reserva">
        <span>Se reservo la ambulancia con patente : * ${ReservaRealizada.getAmbulancia().getPatente()} * </span>
        <br>
        <span>Para la direccion : ${ReservaRealizada.getDireccion()}</span>
        <br>
        <span>Por motivo : ${ReservaRealizada.getMotivo()}</span>
         <br>
        <span>Ante un imprevisto, nos comunicaremos al : ${ReservaRealizada.getTelefono()}</span>
        <br>
        <br>
        <div id="seguimiento">
        <a>Ver seguimiento</a>
        </div>
        </div>
    </c:if>
    <h2>${Error}</h2>

</main>

<footer>
    <div class="link">
        <button class="btn  btn-primary btn-lg active link" Type="Submit"/>
        <a  href="cerrar-sesion" class="link-dark">Cerrar sesion</a></button>
    </div>
</footer>
</body>
</html>