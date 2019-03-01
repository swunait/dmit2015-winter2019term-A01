package northwind.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import northwind.entity.Shipper;

public class ShipperRespository extends AbstractJpaRepository<Shipper> {

	@PersistenceContext(unitName="classicmodelsPU")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public ShipperRespository() {
		super(Shipper.class);
	}
	
}
