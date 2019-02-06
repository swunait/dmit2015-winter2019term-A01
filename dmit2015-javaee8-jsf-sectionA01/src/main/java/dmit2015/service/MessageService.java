package dmit2015.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dmit2015.model.Message;

@Stateless
public class MessageService {

	@PersistenceContext(unitName="developmentDB")
	private EntityManager entityManager;
	
	public void createMessage(Message message) {
		entityManager.persist(message);
	}
	
	public List<Message> list() {
		return entityManager.createQuery("FROM Message m", Message.class).getResultList();
	}
}