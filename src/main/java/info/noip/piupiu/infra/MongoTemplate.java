package info.noip.piupiu.infra;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@ApplicationScoped
@Component
public class MongoTemplate {

	private MongoOperations mongoOperation;

	public MongoOperations getMongoOperations() {
		if (mongoOperation == null) {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(
					SpringMongoConfig.class);
			mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		}

		return mongoOperation;
	}

}
