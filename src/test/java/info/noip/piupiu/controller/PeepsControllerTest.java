package info.noip.piupiu.controller;

import static org.mockito.Mockito.when;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Peep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.vraptor.util.test.MockResult;

@RunWith(MockitoJUnitRunner.class)
public class PeepsControllerTest {

	@Mock
	PostsDao postsDao;
	
	@Mock
	UsersDao usersDao;
	
	@Mock
	UserSession userSession;
	
	User user;
	
	List<Peep> peeps;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		user = new User();
		user.setEmail("teste@gmail.com");
	}
	
	@Test
	public void shouldListPeeps(){
		
		peeps = new ArrayList<Peep>();
		
		Peep peep = new Peep();
		peep.setAuthor("teste@gmail.com");
		peep.setText("Teste");
		peep.setDate(new Date());
		
		peeps.add(peep);
		
		when(userSession.getUser()).thenReturn(user);
		when(postsDao.findByAuthor("teste@gmail.com", 0, 50)).thenReturn(peeps);
		
		MockResult result = new MockResult();
		PeepsController controller = new PeepsController(postsDao, usersDao, result, userSession);
		controller.list(0, 50);
		Assert.assertTrue(result.used());
	}
	
}
