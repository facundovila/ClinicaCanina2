<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/partials/header.jsp"></jsp:include>
<link rel="stylesheet" href="../css/estilos.css">
<body>
<c:if test="${not empty AmbulanciaReservada}">
<h2>Se reservo la ambulancia con patente ${AmbulanciaReservada.getPatente()}</h2>
</c:if>
<h2>${Error}</h2>
</body>
</html>