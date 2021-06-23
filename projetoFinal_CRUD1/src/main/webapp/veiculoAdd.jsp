<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incluir novo veiculo</title>
</head>
<body>
	<c:if test="${veiculo.id == null}">
		<h2>Inclusão de Veiculo</h2>
	</c:if>
	<c:if test="${veiculo.id != null}">
		<h2>Alteração de Veiculo</h2>
	</c:if>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${sucesso}</font>
    <form method="post" action='controllerVeiculo'>
    	<input type="hidden" name="action" value="save" />
        <label >Veiculo ID: </label><br>
        <input type="text" readonly="readonly" name="id" 
        value="<c:out value="${veiculo.id}" />" /> <p />
        
        <label>Modelo: </label><br> 
        <input type="text" required name="modelo" 
        value="<c:out value="${veiculo.modelo}" />" /> <p /> 
        
        <label>Marca: </label><br> 
        <input type="text" required name="marca" 
        value="<c:out value="${veiculo.marca}" />" /> <p /> 
        
        <label>Placa: </label><br> 
        <input type="text" required name="placa" 
        value="<c:out value="${veiculo.placa}" />" /> <p /> 
        
        <label>Renavam: </label><br> 
        <input type="text" required name="renavam" 
        value="<c:out value="${veiculo.renavam}" />" /> <p /> 

		<label>Proprietario: </label><br/>
		<select required name="idProprietario">
			<c:forEach var="proprietario" items="${proprietarios}" >
				<c:if test="${proprietario.id == veiculo.proprietario.id}">
					<option value="${proprietario.id}" selected="selected">${proprietario.nome}
				</c:if>
				<c:if test="${proprietario.id != veiculo.proprietario.id}">
					<option value="${proprietario.id}">${proprietario.nome}
				</c:if>
			</c:forEach>
		</select>
		<p />

		<input type="image" src="IMAGES/salvar.jpg" alt="Salvar">
    </form>
    <a href="controllerVeiculo?action=listVeiculo">Voltar</a><br/>
</body>
</html>