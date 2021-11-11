package org.junit;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.jupiter.api.Test;

public class TestCoord {
	@Before
	ABR t;
	public void initTest() {
		t = new Coord(5,8);
	}
	@Test
	public void CoordTest() {
		assertEquals(t, "(5,8)");
	}
}