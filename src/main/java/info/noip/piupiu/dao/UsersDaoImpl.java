package info.noip.piupiu.dao;

import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Post;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
		saveOnMong(user);
		return user;
	}

	private void saveOnMong(User user) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("mongodb.xml");
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	    
	    Post post = new Post();
	    post.setAuthor(user.getEmail());
	    post.setDate(new Date());
	    post.setText("Estou testando a configuração do mongo");
	    
	    mongoOperation.save(post);
	}

}
