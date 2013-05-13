package info.noip.piupiu.model.mongo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PeepTest {

	Peep peep;
	
	@Before
	public void setUp(){
		peep = new Peep();
		peep.setText("Devemos identificar 2 hashtags #prontofalei #fikdik");
	}
	
	@Test
	public void shouldIdentifyHashTags(){
		peep.addHashTags();
		assertTrue(peep.getHashTags().size() == 2);
		assertTrue(peep.getHashTags().contains("#prontofalei"));
		assertTrue(peep.getHashTags().contains("#fikdik"));
	}
	
	@Test
	public void shouldFormatText(){
		assertTrue(peep.getFormattedText().contains("<a href='#'>#prontofalei</a>"));
	}
	
}
