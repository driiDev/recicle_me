package Application;

import java.sql.Connection;

import java.sql.SQLException;

import persistencia.Conexao;

public class Program {

	public static void main(String[] args) throws SQLException {
		Connection conn = Conexao.conectaBD();
		if(conn != null)
			System.out.println("Conex�o com o banco realizada com sucesso!!!");
		conn.close();
	
		

	}

}
