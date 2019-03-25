package northwind.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import northwind.entity.Customer;
import northwind.entity.Employee;
import northwind.entity.Order;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class OrderQueryResultListController implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private NorthwindService northwindService;
	
	@Inject
	private OrderQuerySingleResultController querySingleResultController;
	
	@NotBlank(message="A customer must be selected")
	@Getter @Setter
	private String queryCustomerID;
	
	@Getter
	private List<Customer> customersWithOrders;
	
	@Getter @Setter	
	private Integer queryEmployeeID;
	
	@Getter
	private List<Employee> employeesWithSalesOrders;
		
	@Getter
	private List<Order> queryResultList;		// +getter
		
	@PostConstruct
	void init() {		
		customersWithOrders = northwindService.findCustomersWithOrders();
		employeesWithSalesOrders = northwindService.findEmployeesWithSalesOrders();
	}
	
	public void findResultListByCustomer() {
		if (queryCustomerID != null) {
			queryResultList = northwindService.findOrdersByCustomerID(queryCustomerID);
			querySingleResultController.setQuerySingleResult(null);
			if (queryResultList == null || queryResultList.size() == 0 ) {
				Messages.addGlobalInfo("There are no orders for customerID {0}.", queryCustomerID);
			} else {
				Messages.addGlobalInfo("There are {0} orders for customerID {1}", queryResultList.size(), queryCustomerID);
			}			
		} else {
			Messages.addGlobalError("A customer must be selected.");
		}
	}
	
	public void findResultListByEmployee() {
		if (queryEmployeeID != null) {
			queryResultList = northwindService.findOrdersByEmployeeID(queryEmployeeID);
			querySingleResultController.setQuerySingleResult(null);
			if (queryResultList == null || queryResultList.size() == 0 ) {
				Messages.addGlobalInfo("There are no orders for employeeID {0}.", queryCustomerID);
			} else {
				Messages.addGlobalInfo("There are {0} orders for employeeID {1}", queryResultList.size(), queryCustomerID);
			}			
		} else {
			Messages.addGlobalError("A customer must be selected.");
		}
	}


	public void findSingleResult(int orderNumber) {
		querySingleResultController.setQueryIdValue(orderNumber);
		querySingleResultController.doFindSingleResult();
	}
	
	public void cancel() {
		querySingleResultController.setQueryIdValue(null);
		querySingleResultController.setQuerySingleResult(null);
		queryCustomerID = null;
		queryResultList = null;
	}
}
