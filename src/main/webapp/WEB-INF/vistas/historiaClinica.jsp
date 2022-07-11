<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="css/estilos.css">
<!doctype html>
<html lang="es">
<head>
    <%@ include file="partials/head.jsp"%>
</head>

<body>
<header>
    <%@ include file="partials/usuarioMenu.jsp"%>
</header>
<main class="fondo">


    <section>
     <article>
        <div class="container">

            <h2 class="mb-3 ">Historia Clinica</h2>

            <div>

                <div class="card border-dark mb-3" style="max-width: 18rem;">
                    <div class="card-header"><h4>${mascota.nombre}</h4></div>
                    <div class="card-body text-dark">
                        <h5 class="card-title">Datos de la ultima visita</h5>
                        <p class="card-text">peso: ${mascota.peso} kg</p>
                        <p class="card-text">edad: ${mascota.edad} </p>
                    </div>
                </div>

                <table class="table table-bordered table-light centrar">
                    <h4>Visitas realizadas</h4>
                    <thead>
                    <tr>
                        <th scope="col" class="text-center">fecha</th>
                        <th scope="col" class="text-center">sintomas</th>
                        <th scope="col" class="text-center">Tratamiento</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${visita}" var="visita">
                    <tr>
                        <td class="text-center" >${visita.fecha}</td>
                        <td class="text-center">${visita.sintomas}</td>
                        <td class="text-center">${visita.tratamiento}</td>


                        </a>
                        </td>
                    </tr>
                        </c:forEach>
                    </tbody>
                </table>



            </div>
        </div>
         <div>
             <p>
                 <a href="modificar-mascota?idMascota=${mascota.id}" class="w3-text-blue">Agregar Visita</a>
             </p>
         </div>
        </article>

    </section>


    <br>
    <br>
    <br>
    <br>
</main>

<footer>
    <div class="link">
        <button class="btn  btn-primary btn-lg active link" Type="Submit"/>
        <a  href="cerrar-sesion" class="link-dark">Cerrar sesion</a></button>
    </div>
</footer>
</body>
</html>

</html>
