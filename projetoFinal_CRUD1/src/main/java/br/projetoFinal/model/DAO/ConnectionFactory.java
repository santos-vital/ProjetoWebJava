package br.projetoFinal.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection con = null;

	public static Connection getConnection() throws SQLException {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String sql = "jdbc:mysql://localhost/db_projeto";
				con = DriverManager.getConnection(sql, "root", "");
			} catch (ClassNotFoundException e) {
				throw new SQLException("Driver n�o localizado");
			}
		}
		return con;
	}

}