<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<link rel="stylesheet" href="../../css/estilos.css">
<link rel="stylesheet" href="../../css/styleAmbulancia.css">

<body>

<main>
    <c:if test="${not empty AmbulanciaDisponible}">
   
    <h3 class="form-signin-heading">Reservar Ambulancia</h3>
   
     <form:form action="reservar-ambulancia" method="POST" modelAttribute="datosReservaAmbulancia">
			    	
					<hr class="colorgraph"><br>
					
                   
					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto datosReservaAmbulancia se guardan los datos ingresados--%>
					<div>
					<form:label path="direccionCalle">Ingrese una Calle : </form:label>
					<br>
					<form:input path="direccionCalle" id="direccionCalle" type="text" class="form-control"  />
					</div>
					<div>
					<form:label path="direccionNumero">Ingrese un Numero de Calle : </form:label>
					<br>
					<form:input path="direccionNumero" id="direccionNumero" type="number" class="form-control"  />
					</div>
					<div>
					<form:label path="telefono">Ingrese Numero de Telefono : </form:label>
					<br>
					<form:input path="telefono" id="telefono" type="text" class="form-control"  />
					</div>
					<form:label path="motivo">Explique brevemente el motivo de su urgencia : </form:label>
					<br>
					<form:input path="motivo" id="motivo" type="text" class="form-control"  />
					</div>
					<div>
					<div id="patente" style = "display : none">
					<form:label path="patente">Reservara la siguiente ambulancia con patente : ${AmbulanciaDisponible.get(0).getPatente()}</form:label>
					<br>
					<form:input path="patente" type="text"  value="${AmbulanciaDisponible.get(0).getPatente()}" class="form-control"/>  
					</div>
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Reservar</button>
				</form:form>

</c:if>

<h2>
    ${SinAmbulancias}
</h2>

    <c:if test="${not empty ReservaRealizada}">
        <h2>Se reservo la ambulancia con patente ${ReservaRealizada.getAmbulancia().getPatente()}</h2>
        <br>
        <h3>Para la direccion : ${ReservaRealizada.getDireccion()}</h3>
        <br>
        <h3>Por motivo : ${ReservaRealizada.getMotivo()}</h3>
         <br>
        <h3>Ante un imprevisto, nos comunicaremos al : ${ReservaRealizada.getTelefono()}</h3>
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