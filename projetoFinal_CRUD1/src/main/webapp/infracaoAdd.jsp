<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incluir nova infração</title>
</head>
<body>
	<c:if test="${infracao.id == null}">
		<h2>Inclusão de infração</h2>
	</c:if>
	<c:if test="${infracao.id != null}">
		<h2>Alteração de infração</h2>
	</c:if>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${sucesso}</font>
    <form method="post" action='controllerInfracao'>
    	<input type="hidden" name="action" value="save" />
        <label >Infracao ID: </label><br>
        <input type="text" readonly="readonly" name="id" 
        value="<c:out value="${infracao.id}" />" /> <p />
        
        <label>Descrição: </label><br> 
        <input type="text" required name="descricao" 
        value="<c:out value="${infracao.descricao}" />" /> <p /> 
        
        <label>Data da infração (dd/mm/aaaa): </label><br> 
        <input type="text" required name="data" 
        value="<c:out value="${infracao.dataDMA}" />" /> <p /> 
        
        <label>Gravidade: </label><br> 
        <input type="text" required name="gravidade" 
        value="<c:out value="${infracao.gravidade}" />" /> <p />
        
        <label>Valor: </label><br> 
        <input type="text" required name="valor" 
        value="<c:out value="${infracao.valor}" />" /> <p />
        
        <label>Local: </label><br> 
        <input type="text" required name="local" 
        value="<c:out value="${infracao.local}" />" /> <p />
        
        <label>Data de vencimento: (dd/mm/aaaa): </label><br> 
        <input type="text" required name="data_vencimento" 
        value="<c:out value="${infracao.data_vencimentoDMA}" />" /> <p />   

		<label>Veículo: </label><br/>
		<select required name="idVeiculo">
			<c:forEach var="veiculo" items="${veiculos}" >
				<c:if test="${veiculo.id == infracao.veiculo.id}">
					<option value="${veiculo.id}" selected="selected">${veiculo.modelo}
				</c:if>
				<c:if test="${veiculo.id != infracao.veiculo.id}">
					<option value="${veiculo.id}">${veiculo.modelo}
				</c:if>
			</c:forEach>
		</select>
		<p />

		<input type="image" src="IMAGES/salvar.jpg" alt="Salvar">
    </form>
    <a href="controllerInfracao?action=listInfracao">Voltar</a><br/>
</body>
</html>