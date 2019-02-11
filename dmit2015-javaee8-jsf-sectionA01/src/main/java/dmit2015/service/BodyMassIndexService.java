package dmit2015.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dmit2015.model.BodyMassIndex;
import dmit2015.model.Message;

@Stateless
public class BodyMassIndexService {

	@PersistenceContext(unitName="developmentDB")
	private EntityManager entityManager;
	
	public void createBodyMassIndex(BodyMassIndex bmi) {
		entityManager.persist(bmi);
	}
	
	public List<BodyMassIndex> list() {
		return entityManager.createQuery("FROM BodyMassIndex bmi", BodyMassIndex.class).getResultList();
	}
}