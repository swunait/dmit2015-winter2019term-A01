package northwind.service;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import northwind.entity.Category;
import northwind.entity.Customer;
import northwind.entity.Employee;
import northwind.entity.Order;
import northwind.entity.Shipper;
import northwind.report.CategorySalesRevenue;
import northwind.repository.CategoryRepository;
import northwind.repository.CustomerRepository;
import northwind.repository.EmployeeRepository;
import northwind.repository.OrderRepository;
import northwind.repository.ProductRepository;
import northwind.repository.ShipperRepository;
import security.service.SecurityInterceptor;

@Stateless	
//@DeclareRoles({"DMIT2015Instructor","DMIT2015Student","NorthwindManagement","NorthwindEmployee"})
//@PermitAll
//@Interceptors({SecurityInterceptor.class})
public class NorthwindService {
	
	@Inject
	private OrderRepository orderRepository;

	@Inject
	private CategoryRepository categoryRepository;

	@Inject
	private ProductRepository productRepository;

	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private EmployeeRepository employeeRepository;

	@Inject
	private ShipperRepository shipperRepository;
	
	
	public List<Order> findOrdersByCustomerID(String customerID) {
		return orderRepository.findByCustomerID(customerID);
	}

	public List<Order> findOrdersByEmployeeID(int employeeID) {
		return orderRepository.findByEmployeeID(employeeID);
	}
	

	public List<Employee> findEmployeesWithSalesOrders() {
		return orderRepository.findEmployeesWithSalesOrders();
	}

	public List<Customer> findCustomersWithOrders() {
		return orderRepository.findCustomersWithOrders();
	}
	
	public List<Employee> findAllEmployee() {
		return employeeRepository.findAll();
	}
	
	public Order findOneOrder(int orderID) {
		return orderRepository.findOneOrder(orderID);
	}
	
//	@RolesAllowed({"DMIT2015Instructor","NorthwindManagement"})
	public List<CategorySalesRevenue> findCategorySalesRevenues() {
		return orderRepository.findCategorySalesRevenues();
	}
	
//	@RolesAllowed({"DMIT2015Instructor","NorthwindManagement"})
	public List<CategorySalesRevenue> findCategorySalesRevenuesByYear(Integer salesYear) {
		return orderRepository.findCategorySalesRevenuesByYear(salesYear);
	}
	
	public List<Integer> findYearsWithOrders() {
		return orderRepository.findYearsWithOrders();
	}
	
	public void createCategory(Category newCategory) {
		categoryRepository.create(newCategory);
	}

	public void updateCategory(Category existingCategory) {
		categoryRepository.edit(existingCategory);
	}
	
	public void deleteCategory(Category existingCategory) {
		categoryRepository.remove(existingCategory);
	}
	
	public Category findOneCategory(int categoryID) {
		return categoryRepository.find(categoryID);
	}
	
	public List<Category> findAllCategory() {
		return categoryRepository.findAllOrderByCategoryName();
	}
	
	public void createShipper(Shipper newShipper) {
		shipperRepository.create(newShipper);
	}

	public void updateShipper(Shipper existingShipper) {
		shipperRepository.edit(existingShipper);
	}
	
	public void deleteShipper(Shipper existingShipper) {
		shipperRepository.remove(existingShipper);
	}
	
	public Shipper findOneShipper(int shipperID) {
		return shipperRepository.find(shipperID);
	}
	
	public List<Shipper> findAllShipper() {
		return shipperRepository.findAllOrderByCompanyName();
	}
}