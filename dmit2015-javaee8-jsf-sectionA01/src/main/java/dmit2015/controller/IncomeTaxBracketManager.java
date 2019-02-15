package dmit2015.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.omnifaces.util.Messages;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import dmit2015.model.IncomeTaxBracket;
import lombok.Getter;

@Named				// allows you to access this bean using JSF-EL #{expression}
@ApplicationScoped	// must implement java.io.Serializable
public class IncomeTaxBracketManager implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ServletContext webContext;
	
	@Getter private List<IncomeTaxBracket> taxBrackets;
	
	@PostConstruct
	void init() {
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		CsvMapper mapper = new CsvMapper();
		try {
//			MappingIterator<IncomeTaxBracket> iter = mapper.readerFor(IncomeTaxBracket.class)
//					.with(schema)
//					.readValues(webContext.getResourceAsStream("/WEB-INF/data/ProvincialPersonalIncomeTaxRates.csv"));
			MappingIterator<IncomeTaxBracket> iter = mapper.readerFor(IncomeTaxBracket.class)
					.with(schema)
					.readValues(Thread.currentThread().getContextClassLoader().getResourceAsStream("/ProvincialPersonalIncomeTaxRates.csv"));
			taxBrackets = iter.readAll();
		} catch(Exception e) {
			Messages.addGlobalFatal("Error reading from CSV file");
		}
	}
}
