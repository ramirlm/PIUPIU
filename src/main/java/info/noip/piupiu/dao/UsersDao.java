package info.noip.piupiu.dao;

import java.util.List;

import info.noip.piupiu.model.User;

public interface UsersDao {

	User save(User user);
	User login(User user);
	List<User> find(String user);
}
