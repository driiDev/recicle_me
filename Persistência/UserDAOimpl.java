package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entities.User;

public class UserDAOimpl implements UserDAO {
	
	Connection conn = null;
	
public UserDAOimpl(Connection conn) {
		this.conn = conn;
	}

public void save(User user) {


	String sql = "INSERT INTO user(nome, email, senha) VALUES (?,?,?)";
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	try {
		//Criar conexao com banco de dados
		conn = Conexao.conectaBD();
		
		//Para executar uma query
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		//Add os valoresque são esperados pela query
		pstm.setString(1, user.getNome());
		pstm.setString(2, user.getEmail());
		pstm.setString(3, user.getSenha());
		
		//Executar a query
		pstm.execute();
		
		System.out.println("Cadastro realizado com sucesso!");
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		//Fechar as conexões
		try {
			if(pstm!=null) {
				pstm.close();
			}
			
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		
}

@Override
public void update(User user) {

	String sql = "UPDATE user SET nome = ?, email = ? , senha = ?" + "WHERE id = ?";
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	try {
		//Criar conexao com o banco
		conn = Conexao.conectaBD();
		
		//Criar classe para executar query
		pstm = conn.prepareStatement(sql);
		
		//Add os valores para atualizar
		pstm.setString(1, user.getNome());
		pstm.setString(2, user.getEmail());
		pstm.setString(3, user.getSenha());
		
		//Qual o ID do registro que deseja atualizar
		pstm.setInt(4, user.getId());
		
		//Executar a query
		pstm.execute();
		
		System.out.println("Dados atualizados com sucesso!");
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstm!=null) {
				pstm.close();
			}
			if(conn!=null) {
				conn.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

@Override
public void deleteByEmail(String email) {


	String sql = "DELETE FROM user WHERE email = ?";
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	try {
		conn = Conexao.conectaBD();
		
		pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, email);
		
		pstm.execute();
		
		System.out.println("Excluído com sucesso!");
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
}	

@Override
public List<User> getUser() {

	String sql = "SELECT * FROM user ";
	
	List<User> usuario = new ArrayList<User>();
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	//Classe que vai recuperar os dados do banco ***SELECT***
	ResultSet rset = null;
	
	try {
		conn = Conexao.conectaBD();
		
		pstm = conn.prepareStatement(sql);
		
		rset = pstm.executeQuery();
		

		while(rset.next()) {
			User user = new User();
			
			//Recuperar o id
			user.setId(rset.getInt("id"));
			//Recuperar o nome
			user.setNome(rset.getString("nome"));
			//Recuperar email
			user.setEmail(rset.getString("email"));
			
			usuario.add(user);
			
		}	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rset!=null) {
				rset.close();
			}
			
			if(pstm!=null) {
				pstm.close();
			}
			
			if(conn!=null) {
				conn.close();
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		}
	
		return usuario;
	}

@Override
public boolean login (User user)  {

	
	String sql = "SELECT * FROM user WHERE email = ? AND senha = ? ";

	Connection conn = null;
	PreparedStatement pstm = null;
	
	try {
		conn = Conexao.conectaBD();
		pstm = conn.prepareStatement(sql);
	
	
    	pstm.setString(1, user.getEmail());
	
		pstm.setString(2, user.getSenha());
		
	    ResultSet rs = pstm.executeQuery();
		
	    if(rs.next()) {
	    	user.setNome(rs.getString("nome"));
	 return true;
	}
	    else {
	    	return false;
	    }
}
	catch (SQLException e) {
		e.printStackTrace();
			  	
	}
	    finally {
		//Fechar as conexões
		try {
			if(pstm!=null) {
				pstm.close();
			}
			
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	  }
return false;
}

}



