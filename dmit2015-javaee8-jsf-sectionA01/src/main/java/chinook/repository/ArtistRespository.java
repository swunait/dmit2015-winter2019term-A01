package chinook.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import chinook.entity.Artist;

public class ArtistRespository extends AbstractJpaRepository<Artist>{

	@PersistenceContext(unitName="ChinookPU")
	private EntityManager entityManager;
	
	public ArtistRespository() {
		super(Artist.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
		
}
