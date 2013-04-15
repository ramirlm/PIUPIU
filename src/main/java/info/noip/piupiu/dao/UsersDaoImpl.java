package info.noip.piupiu.dao;

import javax.persistence.NoResultException;

import org.hibernate.Query;
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

	@Override
	public User login(User user) {
		try {
			Query query = (Query) session
					.createQuery("from User where email = :email and password = :password");
			query.setParameter("email", user.getEmail());
			query.setParameter("password", user.getPassword());
			return (User) query.uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
