package info.noip.piupiu.dao;

import info.noip.piupiu.model.User;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsersDaoImpl implements UsersDao {

	private Session session;

	public UsersDaoImpl(Session session) {
		this.session = session;
	}

	@Override
	public User save(User user) {
		session.save(user);
		return user;
	}

}
