package info.noip.piupiu.dao;

import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.mongo.Peep;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class PostsDaoImpl implements PostsDao{

	private MongoTemplate mongoTemplate;
	
	private PostsDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Peep save(Peep post) {
		mongoTemplate.getMongoOperations().save(post);
		return post;
	}
	
}
