package br.projetoFinal.model.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetoFinal.model.Veiculo;

public class VeiculoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection con;

	public VeiculoDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(Veiculo veiculo) throws SQLException {
		if (veiculo == null)
			return 0;
		String sql="INSERT INTO veiculo (modelo, marca, placa, renavam, Proprietario_id) values (?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, veiculo.getModelo());
		stmt.setString(2, veiculo.getMarca());
		stmt.setString(3, veiculo.getPlaca());
		stmt.setString(4, veiculo.getRenavam());
		stmt.setLong(5, veiculo.getProprietario().getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public int update(Veiculo veiculo) throws SQLException {
		if (veiculo == null)
			return 0;
		String sql="UPDATE veiculo SET modelo=?, marca=?, placa=?, renavam=?, Proprietario_id=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, veiculo.getModelo());
		stmt.setString(2, veiculo.getMarca());
		stmt.setString(3, veiculo.getPlaca());
		stmt.setString(4, veiculo.getRenavam());
		stmt.setLong(5, veiculo.getProprietario().getId());
		stmt.setLong(6, veiculo.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public List<Veiculo> listar() throws SQLException {
		String sql = "SELECT * FROM veiculo";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
		while (rs.next()) {
			Veiculo veiculo = new Veiculo();
			veiculo.setId(rs.getLong("id"));
			veiculo.setModelo(rs.getString("modelo"));
			veiculo.setMarca(rs.getString("marca"));
			veiculo.setPlaca(rs.getString("placa"));
			veiculo.setRenavam(rs.getString("renavam"));
			veiculo.setProprietario(proprietarioDAO.getById(rs.getLong("Proprietario_id")));
			veiculos.add(veiculo);
		}
		rs.close();
		stmt.close();
		return veiculos;	
	}

	public Veiculo getById(long id) throws SQLException {
		Veiculo veiculo = null;
		ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
		String sql = "SELECT * FROM veiculo WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.first()) {
			veiculo = new Veiculo();
			veiculo.setId(rs.getLong("id"));
			veiculo.setModelo(rs.getString("modelo"));
			veiculo.setMarca(rs.getString("marca"));
			veiculo.setPlaca(rs.getString("placa"));
			veiculo.setRenavam(rs.getString("renavam"));
			veiculo.setProprietario(proprietarioDAO.getById(rs.getLong("Proprietario_id")));
		}
		rs.close();
		stmt.close();
		return veiculo;
	}

	public int delete(Veiculo veiculo) throws SQLException {
		if (veiculo == null)
			return 0;
		String sql="DELETE FROM veiculo WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, veiculo.getId());		
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}
