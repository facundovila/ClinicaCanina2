<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<body>
 

<c:if test="${not empty medico}">
    <h2>Los medicos disponibles son</h2>
    <hr class="colorgraph"><br>
    <div class = "container">
    <div id="div" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

    <h3 class="form-signin-heading">Enviar Medico</h3>
    <c:forEach items="${medico}" var="each">
     <form:form action="enviar-medico" method="POST" modelAttribute="datosMedicos">

                    <hr class="colorgraph"><br>
                    <h4>Dni : ${each.dni} </h4>
                    <h4>Nombre medico : ${each.nombre} </h4>
                    <h4>Horario entrada : ${each.horarioEntrada} </h4>
                    <h4>Horario salida : ${each.horarioSalida} </h4>
                    <h4>Disponibilidad : ${each.disponibilidad} </h4>

                    <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                    <div>
                    <form:label path="nombre">Medico : </form:label>
                    <br>
                    <form:input path="nombre" id="nombre" type="text" class="form-control" value="${each.nombre}" />
                    <form:input path="dni" id="dni" type="number" class="form-control" value="${each.dni}" />
                    </div>
				    <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Enviar Medico</button>
                </form:form>
</c:forEach>
 
</div>
</div>
</c:if>
 
<h2>
    ${nohaymedicos}
</h2>
  

</body>
</html>