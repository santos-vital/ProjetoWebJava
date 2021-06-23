package br.projetoFinal.model.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetoFinal.model.Infracao;

public class InfracaoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection con;

	public InfracaoDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(Infracao infracao) throws SQLException {
		if (infracao == null)
			return 0;
		String sql="INSERT INTO infracao (descricao, data, gravidade, valor, local, data_vencimento, Veiculo_id) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, infracao.getDescricao());
		java.sql.Date data = new java.sql.Date(infracao.getData().getTime());
		stmt.setDate(2, data);
		stmt.setString(3, infracao.getGravidade());
		stmt.setFloat(4, infracao.getValor());
		stmt.setString(5, infracao.getLocal());
		java.sql.Date data_vencimento = new java.sql.Date(infracao.getData_vencimento().getTime());
		stmt.setDate(6, data_vencimento);
		stmt.setLong(7, infracao.getVeiculo().getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public int update(Infracao infracao) throws SQLException {
		if (infracao == null)
			return 0;
		String sql="UPDATE infracao SET descricao=?, data=?, gravidade=?, valor=?, local=?, data_vencimento=?, Veiculo_id=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, infracao.getDescricao());
		java.sql.Date data = new java.sql.Date(infracao.getData().getTime());
		stmt.setDate(2, data);
		stmt.setString(3, infracao.getGravidade());
		stmt.setFloat(4, infracao.getValor());
		stmt.setString(5, infracao.getLocal());
		java.sql.Date data_vencimento = new java.sql.Date(infracao.getData_vencimento().getTime());
		stmt.setDate(6, data_vencimento);
		stmt.setLong(7, infracao.getVeiculo().getId());
		stmt.setLong(8, infracao.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public List<Infracao> listar() throws SQLException {
		String sql = "SELECT * FROM infracao";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Infracao> infracoes = new ArrayList<Infracao>();
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		while (rs.next()) {
			Infracao infracao = new Infracao();
			infracao.setId(rs.getLong("id"));
			infracao.setDescricao(rs.getString("descricao"));
			infracao.setData(rs.getDate("data"));
			infracao.setGravidade(rs.getString("gravidade"));
			infracao.setValor(rs.getFloat("valor"));
			infracao.setLocal(rs.getString("local"));
			infracao.setData_vencimento(rs.getDate("data_vencimento"));
			infracao.setVeiculo(veiculoDAO.getById(rs.getLong("Veiculo_id")));
			infracoes.add(infracao);
		}
		rs.close();
		stmt.close();
		return infracoes;	
	}

	public Infracao getById(long id) throws SQLException {
		Infracao infracao = null;
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		String sql = "SELECT * FROM infracao WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.first()) {
			infracao = new Infracao();
			infracao.setId(rs.getLong("id"));
			infracao.setDescricao(rs.getString("descricao"));
			infracao.setData(rs.getDate("data"));
			infracao.setGravidade(rs.getString("gravidade"));
			infracao.setValor(rs.getFloat("valor"));
			infracao.setLocal(rs.getString("local"));
			infracao.setData_vencimento(rs.getDate("data_vencimento"));
			infracao.setVeiculo(veiculoDAO.getById(rs.getLong("Veiculo_id")));
		}
		rs.close();
		stmt.close();
		return infracao;
	}

	public int delete(Infracao infracao) throws SQLException {
		if (infracao == null)
			return 0;
		String sql="DELETE FROM infracao WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, infracao.getId());		
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}
