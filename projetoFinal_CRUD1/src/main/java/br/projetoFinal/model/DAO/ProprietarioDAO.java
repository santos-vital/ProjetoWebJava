package br.projetoFinal.model.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetoFinal.model.Proprietario;

public class ProprietarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection con;

	public ProprietarioDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(Proprietario proprietario) throws SQLException {
		if (proprietario == null)
			return 0;
		String sql="INSERT INTO proprietario (nome, endereco, cidade, cep, uf, cpf) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, proprietario.getNome());
		stmt.setString(2, proprietario.getEndereco());
		stmt.setString(3, proprietario.getCidade());
		stmt.setString(4, proprietario.getCep());
		stmt.setString(5, proprietario.getUf());
		stmt.setString(6, proprietario.getCpf());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public int update(Proprietario proprietario) throws SQLException {
		if (proprietario == null)
			return 0;
		String sql="UPDATE proprietario SET nome=?, endereco=?, cidade=?, cep=?, uf=?, cpf=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, proprietario.getNome());
		stmt.setString(2, proprietario.getEndereco());
		stmt.setString(3, proprietario.getCidade());
		stmt.setString(4, proprietario.getCep());
		stmt.setString(5, proprietario.getUf());
		stmt.setString(6, proprietario.getCpf());
		stmt.setLong(7, proprietario.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public List<Proprietario> listar() throws SQLException {
		String sql = "SELECT * FROM proprietario";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Proprietario> proprietarios = new ArrayList<Proprietario>();
		while (rs.next()) {
			Proprietario proprietario = new Proprietario();
			proprietario.setId(rs.getLong("id"));
			proprietario.setNome(rs.getString("nome"));
			proprietario.setEndereco(rs.getString("endereco"));
			proprietario.setCidade(rs.getString("cidade"));
			proprietario.setCep(rs.getString("cep"));
			proprietario.setUf(rs.getString("uf"));
			proprietario.setCpf(rs.getString("cpf"));
			proprietarios.add(proprietario);
		}
		rs.close();
		stmt.close();
		return proprietarios;	
	}

	public Proprietario getById(long id) throws SQLException {
		Proprietario proprietario = null;
		String sql = "SELECT * FROM proprietario WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.first()) {
			proprietario = new Proprietario();
			proprietario.setId(rs.getLong("id"));
			proprietario.setNome(rs.getString("nome"));
			proprietario.setEndereco(rs.getString("endereco"));
			proprietario.setCidade(rs.getString("cidade"));
			proprietario.setCep(rs.getString("cep"));
			proprietario.setUf(rs.getString("uf"));
			proprietario.setCpf(rs.getString("cpf"));
		}
		rs.close();
		stmt.close();
		return proprietario;
	}

	public int delete(Proprietario proprietario) throws SQLException {
		if (proprietario == null)
			return 0;
		String sql="DELETE FROM proprietario WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, proprietario.getId());		
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}
