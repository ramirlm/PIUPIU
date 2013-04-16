package info.noip.piupiu.dao;

import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Peep;

import java.util.Date;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsersDaoImpl implements UsersDao {

	private Session session;
	private MongoTemplate mongoTemplate;

	public UsersDaoImpl(Session session, MongoTemplate mongoTemplate) {
		this.session = session;
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public User save(User user) {
		session.save(user);
		saveOnMongo(user);
		return user;
	}

	private void saveOnMongo(User user) {
	    Peep post = new Peep();
	    post.setAuthor(user.getEmail());
	    post.setDate(new Date());
	    post.setText("Estou testando a configuração do mongo");
	    
	    mongoTemplate.getMongoOperations().save(post);
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
