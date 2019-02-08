package dmit2015.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.model.BodyMassIndex;

@Named
@RequestScoped
public class BodyMassIndexController {

	private BodyMassIndex currentBMI = new BodyMassIndex();	// +getter
	
	public void calculateBmi() {
		if (currentBMI.bmiClassification().equals("underweight") 
				||
			currentBMI.bmiClassification().equals("overweight")	
				)
		{
			Messages.addGlobalWarn(
					"Your Body Mass Index is {0}. This is considered {1}"
					, currentBMI.bmiValue(), currentBMI.bmiClassification());			
		}
		else if (currentBMI.bmiClassification().equalsIgnoreCase("obese")) {
			Messages.addGlobalFatal(
					"Your Body Mass Index is {0}. This is considered {1}"
					, currentBMI.bmiValue(), currentBMI.bmiClassification());
		} else {
			Messages.addGlobalInfo(
					"Your Body Mass Index is {0}. This is considered {1}"
					, currentBMI.bmiValue(), currentBMI.bmiClassification());			
		}
		
	}

	public BodyMassIndex getCurrentBMI() {
		return currentBMI;
	}
	
}
