<%@page import="com.emergentes.modelo.Seminario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Seminario"%>
<%
    Seminario seminario = (Seminario) request.getAttribute("seminario");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${seminario.id == 0}">
                Nuevo Registro
            </c:if>

            <c:if test="${seminario.id != 0}">
                Editar Registro
            </c:if>

        </h1>
        <form action="Inicio" method="POST">

            <input type="hidden" name="id" value="${seminario.id}"> 

            <table border="1">
                <tr>
                    <td>Titulo:</td>
                    <td><input type="text" name="titulo" value="${seminario.titulo}"></td>
                </tr>
                <tr>
                    <td>Expositor:</td>
                    <td><input type="text" name="titulo" value="${seminario.expositor}"></td>
                </tr>
                <tr>
                    <td>Fecha:</td>
                    <td><input type="text" name="titulo" value="${seminario.fecha}"></td>
                </tr>
                <tr>
                    <td>Hora:</td>
                    <td><input type="text" name="titulo" value="${seminario.hora}"></td>
                </tr>
                <tr>
                    <td>Cupo:</td>
                    <td><input type="number" name="titulo" value="${seminario.cupo}"></td>
                </tr>

                
                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>

                
            </table>
        </form>



    </body>
</html>
