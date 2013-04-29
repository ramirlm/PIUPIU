package info.noip.piupiu.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import info.noip.piupiu.infra.MongoTemplate;
import info.noip.piupiu.model.mongo.Circle;
import info.noip.piupiu.model.mongo.Peep;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

@RunWith(MockitoJUnitRunner.class)
public class PostsDaoImplTest {
	
	@Mock
	MongoTemplate mongoTemplate;
	
	@Mock
	CircleDao circleDao;
	
	@Mock
	MongoOperations mongoOperations;

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void shouldLikePeep(){
		Peep peep = new Peep();
		peep.setId(new ObjectId());
		
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findById(peep.getId(), Peep.class)).thenReturn(peep);
		PostsDao dao = new PostsDaoImpl(mongoTemplate, circleDao);
		dao.like(peep, "eduardohitek@gmail.com");
		assertTrue(peep.getLikers().size() > 0);
		assertEquals(1, peep.getLikers().size());
	}
	
	@Test
	public void shouldDislikePeep(){
		Peep peep = new Peep();
		peep.setId(new ObjectId());
		peep.addLiker("eduardohitek@gmail.com");
		assertTrue(peep.getLikers().size() > 0);
		assertEquals(1, peep.getLikers().size());

		
		when(mongoTemplate.getMongoOperations()).thenReturn(mongoOperations);
		when(mongoTemplate.getMongoOperations().findById(peep.getId(), Peep.class)).thenReturn(peep);
		PostsDao dao = new PostsDaoImpl(mongoTemplate, circleDao);
		dao.dislike(peep, "eduardohitek@gmail.com");
		assertFalse(peep.getLikers().size() > 0);
		assertEquals(0, peep.getLikers().size());
	}


}
