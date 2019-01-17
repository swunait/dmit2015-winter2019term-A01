package dmit2015.demo03;

public class Circle {
	
	/** The radius of the circle */
	double radius;

	/** Construct a circle object */
	public Circle(double newRadius) {
		radius = newRadius;
	}

	/** Construct a circle object */
	public Circle() {
		radius = 1;
	}
	
	/** Return the area of this cirlce */
	double area() {
		return radius * radius * Math.PI;
	}
	
	/** Return the circumference of this circle */
	double circumference() {
		return 2 * radius * Math.PI;
	}
	

}
