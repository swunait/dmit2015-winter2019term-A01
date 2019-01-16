package dmit2015.demo04;

public class Circle {
	
	/** The radius of the circle */
	private double radius;

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

	public double getRadius() {
		return radius;
	}

//	public void setRadius(double radius) throws Exception {
//		if (radius > 0) {
//			this.radius = radius;			
//		} else {
//			throw new Exception("Radius value must be > 0");
//		}
//	}
	
	public void setRadius(double radius) {
		if (radius > 0) {
			this.radius = radius;			
		} else {
			throw new RuntimeException("Radius value must be > 0");
		}
	}

	
	
	
	
	
	
	
	
}
