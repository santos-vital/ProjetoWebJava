<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista de veículos</title>
    </head>
<body>
	<%-- <jsp:forward page="/controller?action=listCurso" />--%> 
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${sucesso}</font>
    <table border=1>
        <thead>
            <tr>
                <th>Codigo Veiculo</th>
                <th>Modelo</th>
                <th>Marca</th>
                <th>Placa</th>
                <th>Renavam</th>
                <th>Proprietario</th>
                <th colspan=2>Ação</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${veiculos}" var="veiculo">
                <tr>
                    <td><c:out value="${veiculo.id}" /></td>
                    <td><c:out value="${veiculo.modelo}" /></td>
                    <td><c:out value="${veiculo.marca}" /></td>
                    <td><c:out value="${veiculo.placa}" /></td>
                    <td><c:out value="${veiculo.renavam}" /></td>
                    <td><c:out value="${veiculo.proprietario.nome}" /></td>
                    <td><a href="controllerVeiculo?action=delete&Id=<c:out value="${veiculo.id}"/>"><img src="IMAGES/excluir.jpg" alt="Excluir"></a></td>
                    <td><a href="controllerVeiculo?action=edit&Id=<c:out value="${veiculo.id}"/>"><img src="IMAGES/editar.jpg" alt="Editar"></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>
    <a href="controllerVeiculo?action=insert"><img src="IMAGES/incluir.jpg" alt="Incluir"></a></p>
    <a href="index.html">Voltar</a>
</body>
</html>