package dmit2015.model;

import javax.validation.constraints.DecimalMin;

import org.apache.commons.math3.util.Precision;

public class BodyMassIndex {

	/** weight in pounds */
	@DecimalMin(value="50", message="Weight must be at least 50lbs")
	private double weight;	// +getter +setter
	/** height in inches */
	@DecimalMin(value="60", message="Height must be at least 60 inches")
	private double height;	// +getter +setter
	
	public double bmiValue() {
		double bmi = 703 * weight / Math.pow(height, 2);
		return Precision.round(bmi, 1);
	}
	
	public String bmiClassification() {
		String classification = "Unknown Classification";
		double bmi = bmiValue();
		if (bmi < 18.5) {
			classification = "underweight";
		} else if (bmi < 25) {
			classification = "normal";
		} else if (bmi < 30) {
			classification = "overweight";
		} else {
			classification = "obese";
		}
		return classification;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public BodyMassIndex() {
		super();
	}

	public BodyMassIndex(double weight, double height) {
		super();
		this.weight = weight;
		this.height = height;
	}
	
	
}
