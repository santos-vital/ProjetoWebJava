package br.projetoFinal.controll;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.projetoFinal.model.Infracao;
import br.projetoFinal.model.DAO.InfracaoDAO;
import br.projetoFinal.model.DAO.VeiculoDAO;

@WebServlet("/controllerInfracao")
public class ControllerInfracao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), destino = null;
		RequestDispatcher dispatcher;
		InfracaoDAO infracaoDAO;
		VeiculoDAO veiculoDAO;
		List<Infracao> infracoes;
		Infracao infracao;
		long id;
		try {
			infracaoDAO = new InfracaoDAO();
			veiculoDAO = new VeiculoDAO();
			if ((action == null) || (action.equals("listInfracao"))) {
				infracoes = infracaoDAO.listar();
				request.setAttribute("infracoes", infracoes);
				destino = "infracaoList.jsp";
			} else if (action.equals("edit")) {
				id = Long.parseLong(request.getParameter("Id"));
				infracao = infracaoDAO.getById(id);
				request.setAttribute("infracao", infracao);
				request.setAttribute("veiculos", veiculoDAO.listar());
				destino = "infracaoAdd.jsp";
			} else if (action.equals("insert")) {
				request.setAttribute("veiculos", veiculoDAO.listar());
				destino = "infracaoAdd.jsp";
			} else if (action.equals("delete")) {
				id = Long.parseLong(request.getParameter("Id"));
				infracao = infracaoDAO.getById(id);
				if (infracaoDAO.delete(infracao) == 0)
					request.setAttribute("erro", "Erro na exclusão. Não excluído!");
				else
					request.setAttribute("sucesso", "Excluído com sucesso!");
				infracoes = infracaoDAO.listar();
				request.setAttribute("infracoes", infracoes);
				destino = "infracaoList.jsp";
			} else if (action.equals("save")) {
				infracao = new Infracao();
				infracao.setDescricao(request.getParameter("descricao"));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				infracao.setData(sdf.parse(request.getParameter("data")));
				infracao.setGravidade(request.getParameter("gravidade"));
				infracao.setValor(Float.parseFloat(request.getParameter("valor").replace(",", ".")));
				infracao.setLocal(request.getParameter("local"));
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				infracao.setData_vencimento(sdf2.parse(request.getParameter("data_vencimento")));
				infracao.setVeiculo(veiculoDAO.getById(Long.parseLong(request.getParameter("idVeiculo"))));
				if (request.getParameter("id").equals("")) {
					if (infracaoDAO.incluir(infracao) == 0)
						request.setAttribute("erro", "Erro na inclusão. Não incluido!");
					else
						request.setAttribute("sucesso", "Incluido com sucesso!");
				} 
				else {
					id = Long.parseLong(request.getParameter("id"));
					infracao.setId(id);
					if (infracaoDAO.update(infracao) == 0)
						request.setAttribute("erro", "Erro na alteração. Não alterado!");
					else
						request.setAttribute("sucesso", "Alterado com sucesso!");
				}
				infracoes = infracaoDAO.listar();
				request.setAttribute("infracoes", infracoes);
				destino = "infracaoList.jsp";
			}
		} catch (SQLException e) {
			System.out.println(e);
			request.setAttribute("erro", "Erro de banco de dados");
			destino = "infracaoList.jsp";
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Erro de conversão");
			destino = "infracaoAdd.jsp";
		} catch (ParseException e) {
			request.setAttribute("erro", "Erro de conversão da data");
			destino = "infracaoAdd.jsp";
		}
		dispatcher = request.getRequestDispatcher(destino);
		dispatcher.forward(request, response);
	}

}
