<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista de infrações</title>
    </head>
<body>
	<%-- <jsp:forward page="/controller?action=listCurso" />--%> 
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${sucesso}</font>
    <table border=1>
        <thead>
            <tr>
                <th>Codigo Infração</th>
                <th>Descrição</th>
                <th>Data da infração</th>
                <th>Gravidade</th>
                <th>Valor</th>
                <th>Local</th>
                <th>Data de vencimento</th>
                <th>Veiculo</th>
                <th colspan=2>Ação</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${infracoes}" var="infracao">
                <tr>
                    <td><c:out value="${infracao.id}" /></td>
                    <td><c:out value="${infracao.descricao}" /></td>
                    <td><fmt:formatDate value="${infracao.data}"/></td>
                    <td><c:out value="${infracao.gravidade}" /></td>
                    <td><fmt:formatNumber
								value="${infracao.valor}" minFractionDigits="2"
								maxFractionDigits="2" currencySymbol="R$" type="currency" /></td>
                    <td><c:out value="${infracao.local}" /></td>
                    <td><fmt:formatDate value="${infracao.data_vencimento}"/></td>
                    <td><c:out value="${infracao.veiculo.modelo}" /></td>
                    <td><a href="controllerInfracao?action=delete&Id=<c:out value="${infracao.id}"/>"><img src="IMAGES/excluir.jpg" alt="Excluir"></a></td>
                    <td><a href="controllerInfracao?action=edit&Id=<c:out value="${infracao.id}"/>"><img src="IMAGES/editar.jpg" alt="Editar"></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>
    <a href="controllerInfracao?action=insert"><img src="IMAGES/incluir.jpg" alt="Incluir"></a></p>
    <a href="index.html">Voltar</a>
</body>
</html>