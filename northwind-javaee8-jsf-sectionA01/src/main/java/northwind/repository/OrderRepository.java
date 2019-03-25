package northwind.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import northwind.entity.Customer;
import northwind.entity.Employee;
import northwind.entity.Order;
import northwind.report.CategorySalesRevenue;

public class OrderRepository extends AbstractNorthwindJpaRepository<Order> {

	public OrderRepository() {
		super(Order.class);
	}
	
	public List<Customer> findCustomersWithOrders() {
		return getEntityManager().createQuery(
			"SELECT o.customer FROM Order o GROUP BY o.customer ORDER BY o.customer.companyName"
			, Customer.class)
			.getResultList();
	}
	
	public List<Order> findByCustomerID(String customerID) {
		return getEntityManager().createQuery(
			"FROM Order o WHERE o.customer.customerID = :idValue ORDER BY o.orderDate DESC"
			, Order.class)
			.setParameter("idValue", customerID)
			.getResultList();
	}
	
	public List<Order> findByEmployeeID(int employeeD) {
		return getEntityManager().createQuery(
			"FROM Order o WHERE o.employee.employeeID = :idValue ORDER BY o.orderDate DESC"
			, Order.class)
			.setParameter("idValue", employeeD)
			.getResultList();
	}
	
	public List<Employee> findEmployeesWithSalesOrders() {
		return getEntityManager().createQuery(
			"SELECT o.employee FROM Order o GROUP BY o.employee ORDER BY o.employee.firstName, o.employee.lastName"
			, Employee.class)
			.getResultList();
	}

	public Order findOneOrder(int orderID) {
		Order singleResult = null;
		try {
			singleResult = getEntityManager().createQuery(
				"FROM Order o JOIN FETCH o.orderDetails "
				+ " WHERE o.orderID = :idValue"
				,Order.class)
				.setParameter("idValue", orderID)
				.getSingleResult();
		} catch(NonUniqueResultException | NoResultException e) {
			// do nothing
			e.printStackTrace();
		}
		
		return singleResult;
	}
	
	public List<CategorySalesRevenue> findCategorySalesRevenues() {
		return getEntityManager().createQuery(
			"SELECT new northwind.report.CategorySalesRevenue( "
				+ "c.categoryName, SUM(od.unitPrice * od.quantity * (1 - od.discount)) AS CategoryTotal "
			+ ")"
			+ " FROM Order o, IN (o.orderDetails) od, IN (od.product) p, IN (p.category) c "
			+ " GROUP BY c.categoryName "
			+ " ORDER BY CategoryTotal DESC ", 
			CategorySalesRevenue.class)
			.getResultList();
	}
	
	public List<CategorySalesRevenue> findCategorySalesRevenuesByYear(Integer salesYear) {
		return getEntityManager().createQuery(
			"SELECT new northwind.report.CategorySalesRevenue( "
				+ "c.categoryName, SUM(od.unitPrice * od.quantity * (1 - od.discount)) AS CategoryTotal "
			+ ")"
			+ " FROM Order o, IN (o.orderDetails) od, IN (od.product) p, IN (p.category) c "
			+ " WHERE o.shippedDate IS NOT NULL AND YEAR(o.shippedDate) = :yearValue"
			+ " GROUP BY c.categoryName "
			+ " ORDER BY CategoryTotal DESC ", 
			CategorySalesRevenue.class)
			.setParameter("yearValue", salesYear)
			.getResultList();
	}
	
	public List<Integer> findYearsWithOrders() {
		return getEntityManager().createQuery(
			"SELECT YEAR(o.orderDate) As OrderYear "
				+ " FROM Order o "
				+ " GROUP BY YEAR(o.orderDate) "
				+ " ORDER BY YEAR(o.orderDate) DESC ", 
			Integer.class)
			.getResultList();
	}
}
