package info.noip.piupiu.dao;

import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Avatar;
import info.noip.piupiu.model.mongo.Circle;
import info.noip.piupiu.model.mongo.Peep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PostsDaoImpl implements PostsDao {

	private MongoTemplate mongoTemplate;
	private CircleDao circleDao;

	public PostsDaoImpl(MongoTemplate mongoTemplate, CircleDao circleDao) {
		this.mongoTemplate = mongoTemplate;
		this.circleDao = circleDao;
	}

	@Override
	public Peep save(Peep post) {
		mongoTemplate.getMongoOperations().save(post);
		return post;
	}

	public List<Peep> findByAuthor(String author, Integer skip, Integer limit) {
		Query query = new Query(Criteria.where("author").is(author));
		query.sort().on("date", Order.DESCENDING);
		query.skip(skip == null ? 0 : skip);
		query.limit(limit == null ? 0 : limit);
		return mongoTemplate.getMongoOperations().find(query, Peep.class);
	}

	@Override
	public List<Peep> retrieveTimeline(User user, Integer skip, Integer limit) {
		Circle circle = circleDao.getCircleByEmail(user.getEmail());
		List<String> following = new ArrayList<String>();
		if (circle != null && circle.getFollowing() != null) {
			Iterator<Avatar> iterator = circle.getFollowing().iterator();
			while (iterator.hasNext()) {
				Avatar avatar = iterator.next();
				following.add(avatar.getUserEmail());
			}
		}
		following.add(user.getEmail());

		Query query = new Query(Criteria.where("author").in(following));
		query.sort().on("date", Order.DESCENDING);
		query.skip(skip == null ? 0 : skip);
		query.limit(limit == null ? 0 : limit);

		return mongoTemplate.getMongoOperations().find(query, Peep.class);
	}

	@Override
	public void like(Peep peep, String likerEmail) {
		peep = mongoTemplate.getMongoOperations().findById(peep.getId(),
				Peep.class);
		peep.addLiker(likerEmail);
		mongoTemplate.getMongoOperations().save(peep);
	}

	@Override
	public Peep retrieveById(Peep peep) {
		return mongoTemplate.getMongoOperations().findById(peep.getId(),
				Peep.class);
	}

	@Override
	public void dislike(Peep peep, String likerEmail) {
		peep = mongoTemplate.getMongoOperations().findById(peep.getId(),
				Peep.class);
		peep.removeLiker(likerEmail);
		mongoTemplate.getMongoOperations().save(peep);
	}

	@Override
	public List<Peep> findByHashTag(String hashtag, Integer skip,
			Integer limit) {
		List<String> hashtags = new ArrayList<String>();
		hashtags.add("#" + hashtag);
		
		Query query = new Query(Criteria.where("hashTags").in(hashtags));
		query.sort().on("date", Order.DESCENDING);
		query.skip(skip == null ? 0 : skip);
		query.limit(limit == null ? 0 : limit);

		return mongoTemplate.getMongoOperations().find(query, Peep.class);
	}

	@Override
	public void deletePeep(Peep peep) {
		mongoTemplate.getMongoOperations().remove(peep);
	}

}
