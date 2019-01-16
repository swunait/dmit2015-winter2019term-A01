package dmit2015.demo04;

public class TestCircle {

	public static void main(String[] args) {
		// Create a circle with radius 1
		Circle circle1 = new Circle();
		System.out.println("The area of the circle of radius " + circle1.getRadius() 
				+ " is " + circle1.area());
		
		// Create a circle with a radius of 25
		Circle circle2 = new Circle(25);
		System.out.println("The area of the circle of radius " + circle2.getRadius()
				+ " is " + circle2.area());
		
		// Modify the circle radius
		try {
			circle2.setRadius(-100);
			System.out.println("The area of the circle of radius " + circle2.getRadius()
			+ " is " + circle2.area());		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		circle2.setRadius(-100);
//		System.out.println("The area of the circle of radius " + circle2.getRadius()
//			+ " is " + circle2.area());		
	}

}
