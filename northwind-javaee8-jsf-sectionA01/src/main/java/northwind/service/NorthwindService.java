package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import northwind.entity.Category;
import northwind.entity.Shipper;
import northwind.report.CategorySalesRevenue;

@Stateless	// Mark this class a stateless EJB
//@ApplicationScoped
//@Transactional
public class NorthwindService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<CategorySalesRevenue> findCategorySalesRevenues() {
		return entityManager.createQuery(
			"SELECT new northwind.report.CategorySalesRevenue( "
				+ "c.categoryName, SUM(od.unitPrice * od.quantity * (1 - od.discount)) AS CategoryTotal "
			+ ")"
			+ " FROM Order o, IN (o.orderDetails) od, IN (od.product) p, IN (p.category) c "
			+ " GROUP BY c.categoryName ", 
			CategorySalesRevenue.class)
			.getResultList();
	}
	
	public void createCategory(Category newCategory) {
		entityManager.persist(newCategory);
	}

	public void updateCategory(Category existingCategory) {
		entityManager.merge(existingCategory);
	}
	
	public void deleteCategory(Category existingCategory) {
		if (!entityManager.contains(existingCategory)) {
			existingCategory = entityManager.merge(existingCategory);			
		}
		entityManager.remove(existingCategory);
	}
	
	public Category findOneCategory(int categoryID) {
		return entityManager.find(Category.class, categoryID);
	}
	
	public List<Category> findAllCategory() {
		return entityManager.createQuery(
				"FROM Category c ORDER BY c.categoryName",
				Category.class
			).getResultList();
	}
	
	public void createShipper(Shipper newShipper) {
//		int nextShipperID = (int) entityManager.createQuery(
//				"SELECT MAX(s.shipperID) + 1 FROM Shipper s"
//				).getSingleResult(); 
//		newShipper.setShipperID(nextShipperID);		
		entityManager.persist(newShipper);
	}

	public void updateShipper(Shipper existingShipper) {
		entityManager.merge(existingShipper);
	}
	
	public void deleteShipper(Shipper existingShipper) {
		if (!entityManager.contains(existingShipper)) {
			existingShipper = entityManager.merge(existingShipper);			
		}
		entityManager.remove(existingShipper);
	}
	
	public Shipper findOneShipper(int ShipperID) {
		return entityManager.find(Shipper.class, ShipperID);
	}
	
	public List<Shipper> findAllShipper() {
		return entityManager.createQuery(
				"FROM Shipper s ORDER BY s.companyName",
				Shipper.class
			).getResultList();
	}
}