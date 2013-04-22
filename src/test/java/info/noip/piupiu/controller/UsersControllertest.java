package info.noip.piupiu.controller;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Circle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllertest {
	
	@Mock
	UsersDao usersDao;
	
	@Mock
	UserSession userSession;
	
	@Mock
	CircleDao circleDao;
	
	@Mock
	Validator validator;
	
	private User user;
	private List<User> users;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		user = createFakeUser("teste@gmail.com","testuser");
		users = new ArrayList<User>();
		users.add(user);
		when(usersDao.find("testUser")).thenReturn(users);
		
		MockResult result = new MockResult();
		UsersController controller = new UsersController(usersDao, result, validator, userSession, circleDao);
		controller.find("testUser");
		Assert.assertTrue(result.used());
	}

	@Test
	public void testFollow() {
		User user = createFakeUser("andersonsilva@gmail.com","AndersonTheSpiderSilva");
		HashSet<String> followers = createFakeListOfEmails("test@gmail.com", "ChaelSonnen", 5);
		Circle circle = createCircleOfAnUser("andersonsilva@gmail.com", followers);
		
		
	}

	@Test
	public void testUnfollow() {
		fail("Not yet implemented");
	}
	
	private User createFakeUser(String email, String name){
		User ret = null;
		ret = new User();
		ret.setEmail(email);
		ret.setName(name);
		return ret;
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
