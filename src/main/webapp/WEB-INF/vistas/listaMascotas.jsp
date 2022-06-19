<jsp:include page="/partials/head.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<main>
    <div class="container">
        <c:if test="${empty sinMascotas}">
            <h4 class="mb-3 text-center">Lista De Mascotas</h4>
            <div class="col-6">


                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col" class="text-center">Nombre</th>
                        <th scope="col" class="text-center">Historia Clinica</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listarmascotas}" var="mascota">
                        <tr>
                            <td class="text-center">${mascota.nombre}</td>
                            <td class="text-center"><a href="historia-clinica?idMascota=${mascota.id}">Ver historia Clinica</a></td>


                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

                <h2>${sinMascotas}</h2>



    </div>
    <br><br><br><br>
</main>
<footer>
    <div class="link">
        <button class="btn  btn-primary btn-lg active link" Type="Submit"/>
        <a  href="cerrar-sesion" class="link-dark">Cerrar sesion</a></button>
</div>
</footer>
</body>
</html>
