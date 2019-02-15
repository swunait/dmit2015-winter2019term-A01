package dmit2015.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties({"BracketNo","Region Abbreviation"})
public @Data class IncomeTaxBracket {

	@JsonProperty(value="Region Name")
	private String regionName;	
	
	@JsonProperty("Tax Year")
	private int taxYear;
	
	@JsonProperty("Starting Income")
	private double startingIncome;
	
	@JsonProperty("Ending Income")
	private double endingIncome;
	
	@JsonProperty("Tax Rate")
	private double taxRate;

	@JsonProperty("Base Tax Amount")
	private double baseTaxAmount;
	

}
