package info.noip.piupiu.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.mongo.Circle;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RunWith(MockitoJUnitRunner.class)
public class CircleDaoImplTest {
	
	@Mock
	MongoTemplate mongoTemplate;
	
	@Mock
	MongoOperations mongoOperations;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFollow() {
		HashSet<String> followers = createFakeListOfEmails("fighter@ufc.com", "Fighter", 5);
		Circle userCircle = createCircleOfAnUser("andersonsilva@gmail.com", followers);
		
		Query userEmail = new Query(Criteria.where("userEmail").is("andersonsilva@gmail.com"));
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findOne(userEmail, Circle.class)).thenReturn(userCircle);
		
		CircleDaoImpl circleDao = new CircleDaoImpl(mongoTemplate);
		circleDao.follow("andersonsilva@gmail.com", "chaelsonnen@gmail.com");
		Assert.assertTrue(userCircle.getFollowing().contains("chaelsonnen@gmail.com"));
	}
	
	@Test
	public void testFirstFollow() {
		Circle userCircle = createCircleOfAnUser("andersonsilva@gmail.com", new HashSet<String>());
		userCircle.addFollowing("chaelsonnen@gmail.com");
		
		Query userEmail = new Query(Criteria.where("userEmail").is("andersonsilva@gmail.com"));
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findOne(userEmail, Circle.class)).thenReturn(null);
		
		CircleDaoImpl circleDao = new CircleDaoImpl(mongoTemplate);
		circleDao.follow("andersonsilva@gmail.com", "chaelsonnen@gmail.com");
		Assert.assertTrue(userCircle.getFollowing().contains("chaelsonnen@gmail.com"));
	}

	@Test
	public void testIsFollowing() {
		HashSet<String> followers = createFakeListOfEmails("fighter@ufc.com", "Fighter", 5);
		Circle userCircle = createCircleOfAnUser("andersonsilva@gmail.com", followers);
		
		Query userEmail = new Query(Criteria.where("userEmail").is("andersonsilva@gmail.com"));
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findOne(userEmail, Circle.class)).thenReturn(userCircle);

		CircleDaoImpl circleDao = new CircleDaoImpl(mongoTemplate);
		circleDao.follow("andersonsilva@gmail.com", "chaelsonnen@gmail.com");
		Assert.assertTrue(circleDao.isFollowing("andersonsilva@gmail.com", "chaelsonnen@gmail.com"));
	}
	
	@Test
	public void testIsNotFollowing() {
		HashSet<String> followers = createFakeListOfEmails("fighter@ufc.com", "Fighter", 5);
		Circle userCircle = createCircleOfAnUser("andersonsilva@gmail.com", followers);
		
		Query userEmail = new Query(Criteria.where("userEmail").is("andersonsilva@gmail.com"));
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findOne(userEmail, Circle.class)).thenReturn(null);

		CircleDaoImpl circleDao = new CircleDaoImpl(mongoTemplate);
		Assert.assertFalse(circleDao.isFollowing("andersonsilva@gmail.com", "chaelsonnen@gmail.com"));
	}
	
	@Test
	public void testIsNotFollowingNewUser() {
		Circle userCircle = new Circle();
		userCircle.setUserEmail("andersonsilva@gmail.com");
		Query userEmail = new Query(Criteria.where("userEmail").is("andersonsilva@gmail.com"));
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findOne(userEmail, Circle.class)).thenReturn(userCircle);

		CircleDaoImpl circleDao = new CircleDaoImpl(mongoTemplate);
		Assert.assertFalse(circleDao.isFollowing("andersonsilva@gmail.com", "chaelsonnen@gmail.com"));
	}



	@Test
	public void testUnfollow() {
		HashSet<String> followers = createFakeListOfEmails("fighter@ufc.com", "Fighter", 5);
		Circle userCircle = createCircleOfAnUser("andersonsilva@gmail.com", followers);
		
		Query userEmail = new Query(Criteria.where("userEmail").is("andersonsilva@gmail.com"));
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findOne(userEmail, Circle.class)).thenReturn(userCircle);
		
		CircleDaoImpl circleDao = new CircleDaoImpl(mongoTemplate);
		circleDao.unfollow("andersonsilva@gmail.com", "chaelsonnen@gmail.com");
		Assert.assertFalse(userCircle.getFollowing().contains("chaelsonnen@gmail.com"));
	}
	
	private HashSet<String> createFakeListOfEmails(String email, String name, int qtd){
		HashSet<String> users = new HashSet<String>();
		for(int i=0; i<qtd; i++){
			users.add(i+"_test@gmail.com");
		}
		return users;
	}
	
	private Circle createCircleOfAnUser(String userEmail, Set<String> followers){
		Circle ret = new Circle();
		ret.setUserEmail(userEmail);
		ret.setFollowers(new HashSet<String>(followers));
		return ret;
	}


}
