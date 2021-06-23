package br.projetoFinal.controll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.projetoFinal.model.Proprietario;
import br.projetoFinal.model.DAO.ProprietarioDAO;

@WebServlet("/controllerProprietario")
public class ControllerProprietario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), destino = null;
		RequestDispatcher dispatcher;
		ProprietarioDAO proprietarioDAO;
		List<Proprietario> proprietarios;
		Proprietario proprietario;
		long id;
		try {
			proprietarioDAO = new ProprietarioDAO();
			if ((action == null) || (action.equals("listProprietario"))) {
				proprietarios = proprietarioDAO.listar();
				request.setAttribute("proprietarios", proprietarios);
				destino = "proprietarioList.jsp";
			} else if (action.equals("edit")) {
				id = Long.parseLong(request.getParameter("Id"));
				proprietario = proprietarioDAO.getById(id);
				request.setAttribute("proprietario", proprietario);
				destino = "proprietarioAdd.jsp";
			} else if (action.equals("delete")) {
				id = Long.parseLong(request.getParameter("Id"));
				proprietario = proprietarioDAO.getById(id);
				if (proprietarioDAO.delete(proprietario) == 0)
					request.setAttribute("erro", "Erro na exclusão. Não excluído!");
				else
					request.setAttribute("sucesso", "Excluído com sucesso!");
				proprietarios = proprietarioDAO.listar();
				request.setAttribute("proprietarios", proprietarios);
				destino = "proprietarioList.jsp";
			} else if (action.equals("save")) {
				proprietario = new Proprietario();
				proprietario.setNome(request.getParameter("nome"));
				proprietario.setEndereco(request.getParameter("endereco"));
				proprietario.setCidade(request.getParameter("cidade"));
				proprietario.setCep(request.getParameter("cep"));
				proprietario.setUf(request.getParameter("uf"));
				proprietario.setCpf(request.getParameter("cpf"));
				if (request.getParameter("id").equals("")) {
					if (proprietarioDAO.incluir(proprietario) == 0)
						request.setAttribute("erro", "Erro na inclusão. Não incluido!");
					else
						request.setAttribute("sucesso", "Incluido com sucesso!");
				} 
				else {
					id = Long.parseLong(request.getParameter("id"));
					proprietario.setId(id);
					if (proprietarioDAO.update(proprietario) == 0)
						request.setAttribute("erro", "Erro na alteração. Não alterado!");
					else
						request.setAttribute("sucesso", "Alterado com sucesso!");
				}
				proprietarios = proprietarioDAO.listar();
				request.setAttribute("proprietarios", proprietarios);
				destino = "proprietarioList.jsp";
			}
			
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
			destino = "proprietarioList.jsp";
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Erro de conversão");
			destino = "proprietarioAdd.jsp";
		}
		dispatcher = request.getRequestDispatcher(destino);
		dispatcher.forward(request, response);
	}

}
