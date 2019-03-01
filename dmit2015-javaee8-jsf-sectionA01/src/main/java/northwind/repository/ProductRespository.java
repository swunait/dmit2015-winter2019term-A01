package northwind.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import northwind.entity.Product;

public class ProductRespository extends AbstractJpaRepository<Product> {

	@PersistenceContext(unitName="NorthwindPU")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public ProductRespository() {
		super(Product.class);
	}
	
}
