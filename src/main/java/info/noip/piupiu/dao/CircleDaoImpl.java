package info.noip.piupiu.dao;

import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.mongo.Avatar;
import info.noip.piupiu.model.mongo.Circle;

import java.util.HashSet;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CircleDaoImpl implements CircleDao {

	private MongoTemplate mongoTemplate;

	public CircleDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void follow(String userEmail, String userToFollowEmail) {
		addToFollowingList(userEmail, userToFollowEmail);
		addToFollowersList(userEmail, userToFollowEmail);
	}

	private void addToFollowingList(String userEmail, String userToFollowEmail) {
		Query query = new Query(Criteria.where("userEmail").is(userEmail));
		Circle userCircle = mongoTemplate.getMongoOperations().findOne(query,
				Circle.class);

		if (userCircle == null) {
			userCircle = new Circle();
			userCircle.setUserEmail(userEmail);
		}

		userCircle.addFollowing(userToFollowEmail);

		mongoTemplate.getMongoOperations().save(userCircle);
	}

	private void addToFollowersList(String userEmail, String userToFollowEmail) {
		Query query = new Query(Criteria.where("userEmail").is(
				userToFollowEmail));
		Circle userCircle = mongoTemplate.getMongoOperations().findOne(query,
				Circle.class);

		if (userCircle == null) {
			userCircle = new Circle();
			userCircle.setUserEmail(userToFollowEmail);
		}

		userCircle.addFollower(userEmail);

		mongoTemplate.getMongoOperations().save(userCircle);
	}

	@Override
	public Boolean isFollowing(String loggedEmailUser, String email) {
		Query query = new Query(Criteria.where("userEmail").is(loggedEmailUser));
		Circle userCircle = mongoTemplate.getMongoOperations().findOne(query,
				Circle.class);

		if (userCircle != null) {
			HashSet<Avatar> following = userCircle.getFollowing();
			Avatar avatar = new Avatar(email);
			return following == null ? false : following.contains(avatar);
		}

		return false;
	}

	@Override
	public void unfollow(String userEmail, String userToUnfollowEmail) {
		removeFromFollowingList(userEmail, userToUnfollowEmail);
		removeFromFollowersList(userEmail, userToUnfollowEmail);
	}

	private void removeFromFollowersList(String userEmail,
			String userToUnfollowEmail) {
		Query query = new Query(Criteria.where("userEmail").is(
				userToUnfollowEmail));
		Circle userCircle = mongoTemplate.getMongoOperations().findOne(query,
				Circle.class);

		if (userCircle == null) {
			userCircle = new Circle();
			userCircle.setUserEmail(userToUnfollowEmail);
		}

		userCircle.removeFollowers(userEmail);

		mongoTemplate.getMongoOperations().save(userCircle);
	}

	private void removeFromFollowingList(String userEmail,
			String userToUnfollowEmail) {
		Query query = new Query(Criteria.where("userEmail").is(userEmail));
		Circle userCircle = mongoTemplate.getMongoOperations().findOne(query,
				Circle.class);

		if (userCircle == null) {
			userCircle = new Circle();
			userCircle.setUserEmail(userEmail);
		}

		userCircle.removeFollowing(userToUnfollowEmail);

		mongoTemplate.getMongoOperations().save(userCircle);

	}

	@Override
	public Circle getCircleByEmail(String email) {
		Query query = new Query(Criteria.where("userEmail").is(email));
		return mongoTemplate.getMongoOperations().findOne(query, Circle.class);
	}

}
