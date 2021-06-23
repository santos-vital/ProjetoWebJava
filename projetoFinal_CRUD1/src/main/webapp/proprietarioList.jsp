<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista de proprietarios</title>
    </head>
<body>
	<%-- <jsp:forward page="/controller?action=listCurso" />--%> 
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${sucesso}</font>
    <table border=1>
        <thead>
            <tr>
                <th>Codigo Proprietario</th>
                <th>Nome</th>
                <th>Endereco</th>
                <th>Cidade</th>
                <th>CEP</th>
                <th>UF</th>
                <th>CPF</th>
                <th colspan=2>Ação</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${proprietarios}" var="proprietario">
                <tr>
                    <td><c:out value="${proprietario.id}" /></td>
                    <td><c:out value="${proprietario.nome}" /></td>
                    <td><c:out value="${proprietario.endereco}" /></td>
                    <td><c:out value="${proprietario.cidade}" /></td>
                    <td><c:out value="${proprietario.cep}" /></td>
                    <td><c:out value="${proprietario.uf}" /></td>
                    <td><c:out value="${proprietario.cpf}" /></td>
                    <td><a href="controllerProprietario?action=delete&Id=<c:out value="${proprietario.id}"/>"><img src="IMAGES/excluir.jpg" alt="Excluir"></a></td>
                    <td><a href="controllerProprietario?action=edit&Id=<c:out value="${proprietario.id}"/>"><img src="IMAGES/editar.jpg" alt="Editar"></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="proprietarioAdd.jsp"><img src="IMAGES/incluir.jpg" alt="Incluir"></a></p>
    <a href="index.html">Voltar</a>
</body>
</html>