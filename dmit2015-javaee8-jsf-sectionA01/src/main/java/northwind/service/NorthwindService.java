package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import northwind.entity.Category;
import northwind.entity.Shipper;
import northwind.repository.ShipperRespository;

@Stateless	// Mark this class a stateless EJB
public class NorthwindService {
	
	@PersistenceContext(unitName="NorthwindPU")
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
				"SELECT c FROM Category c ORDER BY c.categoryName",
				Category.class
			).getResultList();
	}
	
	@Inject
	private ShipperRespository shipperRespository;

	public void addShipper(Shipper newShipper) {
		int nextShipperID = (int) shipperRespository.getEntityManager().createQuery(
				"SELECT MAX(s.shipperID) + 1 FROM Shipper s").getSingleResult();
		newShipper.setShipperID(nextShipperID);
		shipperRespository.create(newShipper);
	}
	
	public void updateShipper(Shipper existingShipper) {
		shipperRespository.edit(existingShipper);
	}
	
	public void deleteShipper(Shipper existingShipper) throws Exception { 
		existingShipper = shipperRespository.getEntityManager().merge(existingShipper);
		if (existingShipper.getOrders().size() > 0 ) {
			throw new Exception("A Shipper with orders cannot be deleted.");
		}
		shipperRespository.remove(existingShipper );
	}
	public void deleteShipper(String shipperCode) throws Exception { 
		Shipper existingShipper = findOneShipper(shipperCode);
		deleteShipper(existingShipper);

	}
	
	public Shipper findOneShipper(String shipperCode) {
		return shipperRespository.find(shipperCode);
	}
	
	public List<Shipper> findAllShippers() {
		return shipperRespository.findAll();
	}
}
