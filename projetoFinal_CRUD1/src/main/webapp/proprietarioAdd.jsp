<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incluir novo proprietario</title>
</head>
<body>
		<c:if test="${proprietario.id == null}">
			<h2>Inclusão de Prorietario</h2>
		</c:if>
		<c:if test="${proprietario.id != null}">
			<h2>Alteração de Proprietario</h2>
		</c:if>
		<font color="#FF0000">${erro}</font>
		<font color="#00FF00">${sucesso}</font>
    	<form method="post" action='controllerProprietario'>
    	<input type="hidden" name="action" value="save" />
        <label >Proprietario ID: </label><br>
        <input type="text" readonly="readonly" name="id" 
        value="<c:out value="${proprietario.id}" />" /> <p />
        
        <label>Nome: </label><br> 
        <input type="text" required name="nome" 
        value="<c:out value="${proprietario.nome}" />" /> <p /> 
        
        <label>Endereço: </label><br> 
        <input type="text" required name="endereco" 
        value="<c:out value="${proprietario.endereco}" />" /> <p /> 
        
        <label>Cidade: </label><br> 
        <input type="text" required name="cidade" 
        value="<c:out value="${proprietario.cidade}" />" /> <p /> 
        
        <label>Cep: </label><br> 
        <input type="text" required name="cep" 
        value="<c:out value="${proprietario.cep}" />" /> <p /> 
        
        <label>UF: </label><br> 
        <input type="text" required name="uf" 
        value="<c:out value="${proprietario.uf}" />" /> <p /> 
        
        <label>CPF: </label><br> 
        <input type="text" required name="cpf" 
        value="<c:out value="${proprietario.cpf}" />" /> <p /> 
        
        <input type="image" src="IMAGES/salvar.jpg" alt="Salvar"><p /> 
    </form>
    <a href="controllerProprietario?action=listProprietario">Voltar</a><br/>
</body>
</html>