package info.noip.piupiu.dao;

import info.noip.piupiu.model.User;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", user.getEmail()));
			criteria.add(Restrictions.eq("password", user.getPassword()));

			return (User) criteria.uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> find(String user) {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.ilike("name", user.toLowerCase(),
				MatchMode.ANYWHERE));
		return (List<User>) criteria.list();
	}

	@Override
	public User getById(Long id) {
		return (User) session.get(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("email", email, MatchMode.EXACT));
		return (User) criteria.uniqueResult();
	}

}
