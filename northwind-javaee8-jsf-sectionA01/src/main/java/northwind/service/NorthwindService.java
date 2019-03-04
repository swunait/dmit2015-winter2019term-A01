package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import northwind.entity.Category;

@Stateless	// Mark this class a stateless EJB
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
				"SELECT c FROM Category c ORDER BY c.categoryName",
				Category.class
			).getResultList();
	}
}