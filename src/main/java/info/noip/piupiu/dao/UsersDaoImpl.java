package info.noip.piupiu.dao;

import info.noip.piupiu.infra.SpringMongoConfig;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Post;

import java.util.Date;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

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
		saveOnMongo(user);
		return user;
	}

	private void saveOnMongo(User user) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	    
	    Post post = new Post();
	    post.setAuthor(user.getEmail());
	    post.setDate(new Date());
	    post.setText("Estou testando a configuração do mongo");
	    
	    mongoOperation.save(post);
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
