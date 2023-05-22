package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Tappa;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TappaDAO {
	private final EntityManager em;

	public TappaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tappa e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Tappa salvata!");

	}

	public Tappa getByISBN(UUID id) {

		Tappa found = em.find(Tappa.class, id);
		return found;
	}

	public void delete(UUID id) {
		Tappa found = em.find(Tappa.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Tappa con id " + id + " eliminata!");
		} else {
			log.info("Tappa con id " + id + " non trovata!");
		}
	}
}
