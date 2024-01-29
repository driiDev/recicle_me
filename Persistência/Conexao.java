package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	public static Connection conectaBD() {
		Connection conn = null;

		try {
			String url = "jdbc:mysql://localhost:3306/usuario?user=root&password=root";
			conn = DriverManager.getConnection(url);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
			
		return conn;

	}
	
}