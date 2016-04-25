package kontroler;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domen.Sala;

public class KontrolerTest {
	private static LinkedList<Sala> sale;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sale = new LinkedList<>();
		String nazivSale = "";
		for (int i = 0; i < 10; i++) {
			Sala s = new Sala(i,nazivSale+i, "RC");
			sale.add(s);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		sale = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {		
	}

	@Test
	public void testdaLiPostojiNekaSlobodnaSalaUTerminu() {
		assertTrue(Kontroler.daLiPostojiNekaSlobodnaSalaUTerminu(sale));		
	}
	
	

}
