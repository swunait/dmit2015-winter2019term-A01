package northwind.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import northwind.report.CategorySalesRevenue;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class CategorySalesReportController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindService northwindService;
	
	@Getter @Setter private Integer selectedSalesYear;
	@Getter private List<Integer> orderYears;
	
	@Getter private List<CategorySalesRevenue> categorySalesRevenues;
	
	@PostConstruct
	void init() {
		try {
			categorySalesRevenues = northwindService.findCategorySalesRevenues();
			orderYears = northwindService.findYearsWithOrders();
		} catch(EJBAccessException e) {
			Messages.addGlobalWarn("You do not have permission this resource.");
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving category sales report");
		}
	}
	
	public void generateReport() {
		try {
			if (selectedSalesYear == null) {
				categorySalesRevenues = northwindService.findCategorySalesRevenues();		
			} else {
				categorySalesRevenues = northwindService.findCategorySalesRevenuesByYear(
						selectedSalesYear);
			}
		} catch(EJBAccessException e) {
			Messages.addGlobalWarn("You do not have permission this resource.");
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving category sales report");
		}
	}

}
