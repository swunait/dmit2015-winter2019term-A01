package dmit2015.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.model.BodyMassIndex;
import dmit2015.service.BodyMassIndexService;

@Named
@RequestScoped
public class BodyMassIndexJpaController {

	private BodyMassIndex currentBMI = new BodyMassIndex();	// +getter
	private List<BodyMassIndex> bmis;	// +getter
	
	@Inject
	private BodyMassIndexService bmiService;
	
	@PostConstruct
	public void init() {
		bmis = bmiService.list();
	}
	
	public void createBMI() {
		bmiService.createBodyMassIndex(currentBMI);
		bmis.add(currentBMI);
		currentBMI = new BodyMassIndex();
	}
	
	public void calculate() {
		String message = String.format("Your Body Mass Index is %s. This is considered %s ", 
			currentBMI.bmiValue(), currentBMI.bmiClassification()	);
		if (currentBMI.bmiClassification().equalsIgnoreCase("underweight")) {
			Messages.addGlobalWarn(message);
		} else if (currentBMI.bmiClassification().equalsIgnoreCase("normal")) {
			Messages.addGlobalInfo(message);
		} else if (currentBMI.bmiClassification().equalsIgnoreCase("overweight")) {
			Messages.addGlobalError(message);
		} else {
			Messages.addGlobalFatal(message);
		}
	}

	public BodyMassIndex getCurrentBMI() {
		return currentBMI;
	}

	public List<BodyMassIndex> getBmis() {
		return bmis;
	}
	
}
