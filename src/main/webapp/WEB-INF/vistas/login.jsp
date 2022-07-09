<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="partials/head.jsp"%>
</head>
<body>
<header>
    <h1 class="text-center">CLINICA CANINA</h1>
</header>
<main>

    <section class="contenedor ">

        <article class="contenido1 ">
            <div>
                <img src="img/fondoPerruno.jpg" alt="" class="fotoLogin">
            </div>
        </article>

        <article class="contenido2">
            <h3>Iniciar sesion</h3>
            <div class="w3-col s9 w3-center">
                <form:form action="validar-login" method="POST"
                           modelAttribute="datosLogin">

                    <p class="w3-center w3-margin-top">
                        <form:input path="email" id="email" type="email" class="w3-input"
                                    placeholder="Email" />
                    </p>
                    <p class="w3-center w3-margin-top">
                        <form:input path="password" type="password" id="password"
                                    class="w3-input" placeholder="Contraseña" />
                    </p>
                    <c:if test="${not empty error}">
                        <div class="w3-panel w3-blue w3-padding-16">${error}</div>
                    </c:if>
                    <p>
                        <button class="w3-btn w3-section w3-green w3-ripple w3-block"
                                Type="Submit">Login</button>
                    </p>
                    <p>
                        <a href="registro" class="w3-text-white">Registrarme</a>
                    </p>
                </form:form>
            </div>
        </article>
    </section>







</main>

</body>

</html>
