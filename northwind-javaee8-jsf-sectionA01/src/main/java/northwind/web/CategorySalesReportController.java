package northwind.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import lombok.Getter;
import northwind.report.CategorySalesRevenue;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class CategorySalesReportController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindService northwindService;
	
	@Getter private List<CategorySalesRevenue> categorySalesRevenues;
	
	@PostConstruct
	void init() {
		try {
			categorySalesRevenues = northwindService.findCategorySalesRevenues();			
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving category sales report");
		}
	}

}
