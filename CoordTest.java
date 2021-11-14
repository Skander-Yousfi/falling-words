package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.Coord;

class CoordTest {
	protected Coord a;
	
	@BeforeEach
	void setUp(int x, int y) throws Exception {
		a = new Coord(x,y) ;
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void TestCoordX() throws Exception {
		assertEquals(a.getX(),x);
	}
	@Test
	void TestCoordY() throws Exception {
		assertEquals(a.getY(),y);
	}
	@Test
	void TestCoordDown() throws Exception {
		a.down();
		assertEquals(a.getY()+1,y+1);
	}


}
