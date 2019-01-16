package dmit2015.demo04;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void testArea() {
		Circle circle1 = new Circle();
		// The radius should be 1
		assertEquals(1, circle1.getRadius(), 0);
		// The area should be 3.14
		assertEquals(Math.PI, circle1.area(), 0);
		circle1.setRadius(25);
		// The area should be 1963.4954
		assertEquals(1963.4954, circle1.area(), 0.00001);
	}

	@Test
	public void testCircumference() {
		fail("Not yet implemented");
	}

}
