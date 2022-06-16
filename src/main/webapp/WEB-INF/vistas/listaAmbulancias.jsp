<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<link rel="stylesheet" href="../css/estilos.css">
<body>


<c:if test="${not empty AmbulanciaDisponible}">
    <h2>A continuacion se detallan la/s ambulancia/s disponible/s : </h2>
    <hr class="colorgraph"><br>
    <div class = "container">
	<div id="div" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
   
    <h3 class="form-signin-heading">Reservar Ambulancia</h3>
    <c:forEach items="${AmbulanciaDisponible}" var="each">
     <form:form action="reservar-ambulancia" method="POST" modelAttribute="datosReservaAmbulancia">
			    	
					<hr class="colorgraph"><br>
					<h4>Ambulancia con patente : ${each.patente} </h4>
                   
					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<div>
					<form:label path="direccion">Ingrese Direccion : </form:label>
					<br>
					<form:input path="direccion" id="direccion" type="text" class="form-control" />
					</div>
					<div>
					<form:label path="patente">Reservara la siguiente ambulancia con patente : </form:label>
					<br>
					<form:input path="patente" type="text" id="patente" value="${each.patente}" class="form-control"/>    		  
					</div>
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Reservar</button>
				</form:form>
</c:forEach>

</div>
</div>
</c:if>

<h2>
    ${SinAmbulancias}
</h2>


</body>
</html>