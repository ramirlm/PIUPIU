package info.noip.piupiu.dao;

import static org.junit.Assert.*;
import info.noip.piupiu.infra.MongoTemplate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class PostsDaoImplTest {
	
	@Mock
	MongoTemplate mongoTemplate;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByAuthor() {
		PostsDaoImpl posts = new PostsDaoImpl(mongoTemplate);
	}

}
