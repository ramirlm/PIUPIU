package info.noip.piupiu.dao;

import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.mongo.Peep;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PostsDaoImpl implements PostsDao{

	private MongoTemplate mongoTemplate;
	
	public PostsDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Peep save(Peep post) {
		mongoTemplate.getMongoOperations().save(post);
		return post;
	}
	
	public List<Peep> findByAuthor(String author, Integer skip, Integer limit){
		Query query = new Query(Criteria.where("author")
				.is(author));
		query.sort().on("date", Order.DESCENDING);
		query.skip(skip == null ? 0 : skip);
		query.limit(limit == null ? 0 : limit);
		return mongoTemplate.getMongoOperations().find(query, Peep.class);
	}
	
}
