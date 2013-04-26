package info.noip.piupiu.controller;

import static org.mockito.Mockito.when;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Peep;
import info.noip.piupiu.security.UserSession;

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
		
		Peep peep = createFakePeep("teste@gmail.com", "Teste");
		peeps.add(peep);
		
		when(userSession.getUser()).thenReturn(user);
		when(postsDao.findByAuthor("teste@gmail.com", 0, 50)).thenReturn(peeps);
		
		MockResult result = new MockResult();
		PeepsController controller = new PeepsController(postsDao, result, userSession);
		controller.list(0, 50);
		Assert.assertTrue(result.used());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shoudShow(){
		
		peeps = new ArrayList<Peep>();
		
		Peep peep = createFakePeep("teste@gmail.com", "Teste1");
		peeps.add(peep);
		peep = createFakePeep("teste@gmail.com", "Teste2");
		peeps.add(peep);
		
		when(userSession.getUser()).thenReturn(user);
		when(postsDao.retrieveTimeline(user, 0, 50)).thenReturn(peeps);
		MockResult result = new MockResult();
		PeepsController controller = new PeepsController(postsDao, result, userSession);
		controller.show();
		List<Peep> list = (List<Peep>)result.included().get("peeps");
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void shouldSave(){
		peeps = new ArrayList<Peep>();
		Peep peep = createFakePeep("teste@gmail.com", "Teste1");
		peeps.add(peep);
		
		when(userSession.getUser()).thenReturn(user);
		when(postsDao.save(peep)).thenReturn(peep);
		MockResult result = new MockResult();
		PeepsController controller = new PeepsController(postsDao, result, userSession);
		controller.save(peep);
		Peep peepSaved = (Peep) result.included().get("peep");
		Assert.assertEquals(peep, peepSaved);
		
	}
	
	private Peep createFakePeep(String author, String text){
		Peep peep = new Peep();
		peep.setAuthor(author);
		peep.setText(text);
		peep.setDate(new Date());
		return peep;
		
	}
	
}
