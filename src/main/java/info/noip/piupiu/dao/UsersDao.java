package info.noip.piupiu.dao;

import info.noip.piupiu.model.User;

public interface UsersDao {

	User save(User user);
	User login(User user);
}
