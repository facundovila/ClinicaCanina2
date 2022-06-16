<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<body>


<c:if test="${not empty AmbulanciaDisponible}">
    <h2>A continuacion se detallan la/s ambulancia/s disponible/s : </h2>
    <c:forEach items="${AmbulanciaDisponible}" var="each">
    ${each.patente} <form:form action="reservar-ambulancia" method="POST" modelAttribute="datosReservaAmbulancia">
			    	<h3 class="form-signin-heading">Reservar Ambulancia</h3>
					<hr class="colorgraph"><br>

					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<form:input path="direccion" id="direccion" type="text" class="form-control" />
					<form:input path="ambulancia" type="text" id="ambulancia" value = "${each.patente}"class="form-control"/>     		  
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Reservar</button>
				</form:form>
</c:forEach>

</c:if>


<h2>Sin ambulancias disponibles por el momento</h2>

<h4>
    ${SinAmbulancias}
</h4>


</body>
</html>