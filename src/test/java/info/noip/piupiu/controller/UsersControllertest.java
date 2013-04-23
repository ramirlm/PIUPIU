package info.noip.piupiu.controller;

import static org.mockito.Mockito.when;
import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;

import java.util.ArrayList;
import java.util.List;

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
public class UsersControllerTest {
	
	@Mock
	UsersDao usersDao;
	
	@Mock
	UserSession userSession;
	
	@Mock
	CircleDao circleDao;
	
	@Mock
	Validator validator;
	
	@Mock
	IndexController indexController;
	
	private User user;
	private List<User> users;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setName("Dana White");
		user.setEmail("dana@ufc.com");
		userSession = new UserSession();
		userSession.setUser(user);
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void testSave() {
		user = createFakeUser("teste@gmail.com","testuser");
		user.setPassword("trolololo");
		MockResult result = new MockResult();
		when(validator.onErrorRedirectTo(IndexController.class)).thenReturn(indexController);
		UsersController controller = new UsersController(usersDao, result, validator, userSession, circleDao);
		controller.save(user);
		Assert.assertTrue(result.used());
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
	public void testFindNull() {
		user = createFakeUser("teste@gmail.com","testuser");
		users = new ArrayList<User>();
		users.add(user);
		when(usersDao.find("testUser")).thenReturn(users);
		
		MockResult result = new MockResult();
		UsersController controller = new UsersController(usersDao, result, validator, userSession, circleDao);
		controller.find(null);
		Assert.assertFalse(result.used());
	}

	@Test
	public void testFollow() {
		User userTest = createFakeUser("andersonsilva@ufc.com","AndersonTheSpiderSilva");
		MockResult result = new MockResult();
		when(userSession.getUser()).thenReturn(user);
		UsersController controller = new UsersController(usersDao, result, validator, userSession, circleDao);
		controller.follow(userTest);
		Assert.assertTrue(result.used());
	}

	@Test
	public void testUnfollow() {
		User userTest = createFakeUser("andersonsilva@ufc.com","AndersonTheSpiderSilva");
		MockResult result = new MockResult();
		when(userSession.getUser()).thenReturn(user);
		UsersController controller = new UsersController(usersDao, result, validator, userSession, circleDao);
		controller.unfollow(userTest);
		Assert.assertTrue(result.used());
	}
	
	private User createFakeUser(String email, String name){
		User ret = null;
		ret = new User();
		ret.setEmail(email);
		ret.setName(name);
		return ret;
	}

}
