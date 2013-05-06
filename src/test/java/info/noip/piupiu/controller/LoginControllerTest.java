package info.noip.piupiu.controller;

import static org.mockito.Mockito.when;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.security.UserSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
	
	@Mock
	UsersDao usersDao;
	
	@Mock
	Validator validator;
	
	@Mock
	LoginController loginController;
	
	UserSession userSession;
	
	@Before
	public void setUp(){
		userSession = new UserSession();
		User user = new User();
		user.setName("Junior Cigano");
		user.setEmail("cigano@ufc.com");
		userSession.setUser(user);
	}

	@Test
	public void testLogin() {
		User user = createFakeUser("jonesbones@ufc.com", "Jon Bones Jones");
		when(usersDao.login(user)).thenReturn(user);
		MockResult result = new MockResult();
		when(validator.onErrorRedirectTo(LoginController.class)).thenReturn(loginController);
		LoginController controller = new LoginController(result, userSession, usersDao, validator);
		controller.login(user);
		Assert.assertTrue(result.used());
	}

	@Test
	public void testLogout() {
	
	}
	
	private User createFakeUser(String email, String name){
		User ret = null;
		ret = new User();
		ret.setEmail(email);
		ret.setName(name);
		return ret;
	}

}
