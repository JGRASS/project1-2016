package domen;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SalaTest {

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
	public void testSala() {
		Sala s = new Sala(1, "b009", "amfiteatar");
		assertEquals(1, 1);
		assertEquals("b009", s.getNaziv_sale());
		assertEquals("amfiteatar", s.getTip_sale());
	}

}
