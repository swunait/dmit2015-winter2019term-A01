package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import northwind.entity.Category;
import northwind.entity.Shipper;

@Stateless	// Mark this class a stateless EJB
//@ApplicationScoped
//@Transactional
public class NorthwindService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void createCategory(Category newCategory) {
		entityManager.persist(newCategory);
	}

	public void updateCategory(Category existingCategory) {
		entityManager.merge(existingCategory);
	}
	
	public void deleteCategory(Category existingCategory) {
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
		
		entityManager.persist(newShipper);
	}

	public void updateShipper(Shipper existingShipper) {
		entityManager.merge(existingShipper);
	}
	
	public void deleteShipper(Shipper existingShipper) {
		existingShipper = entityManager.merge(existingShipper);
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