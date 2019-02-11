package dmit2015.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;

import org.apache.commons.math3.util.Precision;

@Entity
public class BodyMassIndex implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;		// +getter +setter
	
	private String name;	// +getter +setter 
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
