package projet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The Class CoordTest.
 */
class CoordTest {
	
	protected Coord a;

	/**
	 * Test coord X.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void TestCoordX() throws Exception {
		a = new Coord(1,2) ;
		assertEquals(a.getX(),1);
	}
	
	/**
	 * Test coord Y.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void TestCoordY() throws Exception {
		a = new Coord(1,2) ;
		assertEquals(a.getY(),2);
	}
	
	/**
	 * Test coord down.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void TestCoordDown() throws Exception {
		a = new Coord(1,2) ;
		a.down();
		assertEquals(a.getY(),3);
	}


}
