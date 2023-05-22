package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Distributore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DistributoreDAO {
	private final EntityManager em;

	public DistributoreDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Distributore e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Distributore salvato!");

	}

	public Distributore getByISBN(UUID id) {

		Distributore found = em.find(Distributore.class, id);
		return found;
	}

	public void delete(UUID id) {
		Distributore found = em.find(Distributore.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Distributore con id " + id + " eliminato!");
		} else {
			log.info("Distributore con id " + id + " non trovato!");
		}
	}
}
