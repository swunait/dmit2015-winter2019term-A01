package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
//import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;

import northwind.entity.Category;
import northwind.entity.Order;
import northwind.entity.Shipper;
import northwind.report.CategorySalesRevenue;
import northwind.repository.CategoryRepository;
import northwind.repository.CustomerRepository;
import northwind.repository.EmployeeRepository;
import northwind.repository.OrderRepository;
import northwind.repository.ProductRepository;
import northwind.repository.ShipperRepository;

@Stateless	
//@ApplicationScoped
//@Transactional
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
	
	public Order findOneOrder(int orderID) {
		return orderRepository.findOneOrder(orderID);
	}
	
	public List<CategorySalesRevenue> findCategorySalesRevenues() {
		return orderRepository.findCategorySalesRevenues();
	}
	
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