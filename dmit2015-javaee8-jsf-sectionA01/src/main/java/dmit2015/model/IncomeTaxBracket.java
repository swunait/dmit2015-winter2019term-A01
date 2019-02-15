package dmit2015.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class IncomeTaxBracket {

	@JsonProperty("BracketNo")
	private int bracketNo;
	
	@JsonProperty(value="Region Abbreviation")
	private String regionAbbreviation;
	
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
