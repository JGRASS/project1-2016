package domen;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvent() {
		Termin t = new Termin(1, 1, 1);
		Sala s = new Sala(1, "b009", "amfiteatar");
		Event e = new Event("Programiranje 1", s, t);
		assertEquals("Programiranje 1", e.getHost());
		assertEquals(s, e.getSala());
		assertEquals(t, e.getTermin());
		
	}

}
