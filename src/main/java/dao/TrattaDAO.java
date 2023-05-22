package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Tratta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrattaDAO {
	private final EntityManager em;

	public TrattaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tratta e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Tratta salvata!");

	}

	public Tratta getByISBN(UUID id) {

		Tratta found = em.find(Tratta.class, id);
		return found;
	}

	public void delete(UUID id) {
		Tratta found = em.find(Tratta.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Tratta con id " + id + " eliminata!");
		} else {
			log.info("Tratta con id " + id + " non trovata!");
		}
	}
}
