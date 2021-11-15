package projet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * La classe CoordTest.
 */
class CoordTest {
	
	protected Coord a;

	/**
	 * Test pour vérifier que l'abcisse retournée par le getter getX() est bien égale à l'abcisse initialisée de a.
	 *
	 * @throws Exception
	 */
	@Test
	void TestCoordX() throws Exception {
		a = new Coord(1,2) ;
		assertEquals(a.getX(),1);
	}
	
	/**
	 * Test pour vérifier que l'ordonnée retournée par le getter getY() est bien égale à l'ordonnée initialisée de a.
	 *
	 * @throws Exception 
	 */
	@Test
	void TestCoordY() throws Exception {
		a = new Coord(1,2) ;
		assertEquals(a.getY(),2);
	}
	
	/**
	 * Test pour vérifier que l'ordonnée soit bien modifiée lors de l'appel de la méthode down
	 *
	 * @throws Exception
	 */
	@Test
	void TestCoordDown() throws Exception {
		a = new Coord(1,2) ;
		a.down();
		assertEquals(a.getY(),3);
	}


}
