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

import br.projetoFinal.model.Veiculo;
import br.projetoFinal.model.DAO.ProprietarioDAO;
import br.projetoFinal.model.DAO.VeiculoDAO;

@WebServlet("/controllerVeiculo")
public class ControllerVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), destino = null;
		RequestDispatcher dispatcher;
		ProprietarioDAO proprietarioDAO;
		VeiculoDAO veiculoDAO;
		List<Veiculo> veiculos;
		Veiculo veiculo;
		long id;
		try {
			veiculoDAO = new VeiculoDAO();
			proprietarioDAO = new ProprietarioDAO();
			if ((action == null) || (action.equals("listVeiculo"))) {
				veiculos = veiculoDAO.listar();
				request.setAttribute("veiculos", veiculos);
				destino = "veiculoList.jsp";
			} else if (action.equals("edit")) {
				id = Long.parseLong(request.getParameter("Id"));
				veiculo = veiculoDAO.getById(id);
				request.setAttribute("veiculo", veiculo);
				request.setAttribute("proprietarios", proprietarioDAO.listar());
				destino = "veiculoAdd.jsp";
			} else if (action.equals("insert")) {
				request.setAttribute("proprietarios", proprietarioDAO.listar());
				destino = "veiculoAdd.jsp";
			} else if (action.equals("delete")) {
				id = Long.parseLong(request.getParameter("Id"));
				veiculo = veiculoDAO.getById(id);
				if (veiculoDAO.delete(veiculo) == 0)
					request.setAttribute("erro", "Erro na exclusão. Não excluído!");
				else
					request.setAttribute("sucesso", "Excluído com sucesso!");
				veiculos = veiculoDAO.listar();
				request.setAttribute("veiculos", veiculos);
				destino = "veiculoList.jsp";
			} else if (action.equals("save")) {
				veiculo = new Veiculo();
				veiculo.setModelo(request.getParameter("modelo"));
				veiculo.setMarca(request.getParameter("marca"));
				veiculo.setPlaca(request.getParameter("placa"));
				veiculo.setRenavam(request.getParameter("renavam"));
				veiculo.setProprietario(proprietarioDAO.getById(Long.parseLong(request.getParameter("idProprietario"))));
				if (request.getParameter("id").equals("")) {
					if (veiculoDAO.incluir(veiculo) == 0)
						request.setAttribute("erro", "Erro na inclusão. Não incluido!");
					else
						request.setAttribute("sucesso", "Incluido com sucesso!");
				} 
				else {
					id = Long.parseLong(request.getParameter("id"));
					veiculo.setId(id);
					if (veiculoDAO.update(veiculo) == 0)
						request.setAttribute("erro", "Erro na alteração. Não alterado!");
					else
						request.setAttribute("sucesso", "Alterado com sucesso!");
				}
				veiculos = veiculoDAO.listar();
				request.setAttribute("veiculos", veiculos);
				destino = "veiculoList.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
			destino = "veiculoList.jsp";
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Erro de conversão");
			destino = "veiculoAdd.jsp";
		} 
		
		dispatcher = request.getRequestDispatcher(destino);
		dispatcher.forward(request, response);
	}

}
