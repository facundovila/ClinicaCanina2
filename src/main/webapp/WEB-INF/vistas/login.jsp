<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/partials/header.jsp"></jsp:include>
<main>


    <section class="contenedor ">

        <article class="contenido1 ">
            <div>
                <img src="img/fondoPerruno.jpg" alt="" class="fotoLogin">
            </div>
        </article>

        <article class="contenido2">
            <h3>Iniciar sesion</h3>
            <form:form action="validar-login" method="POST" modelAttribute="datosLogin">
                <div class="form-group mb-2">
                    <label for="dni">Dni</label>

                    <form:input path="dni" id="dni"  class="form-control" />

                </div>



                <div class="form-group mb-2">
                    <label for="contrasenia"> contraseña</label>

                    <form:input path="contrasenia" type="password" id="contrasenia" class="form-control"/>

                </div>

                <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Iniciar sesion</button>
            </form:form>

            <c:if test="${not empty error}">
                <h4><span>${error}</span></h4>
                <br>
            </c:if>
        </article>
    </section>







</main>

</body>

</html>
