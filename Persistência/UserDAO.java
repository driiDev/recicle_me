package persistencia;

import java.util.List;

import entities.User;

public interface UserDAO {

	void save(User user);

	void update(User user);

	void deleteByEmail(String email);
	
	boolean login(User user);
	 
	List<User> getUser();
	

}
