package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Mezzo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MezzoDAO {
	private final EntityManager em;

	public MezzoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Mezzo e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Mezzo salvato!");

	}

	public Mezzo getByISBN(UUID id) {

		Mezzo found = em.find(Mezzo.class, id);
		return found;
	}

	public void delete(UUID id) {
		Mezzo found = em.find(Mezzo.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Mezzo con id " + id + " eliminato!");
		} else {
			log.info("Mezzo con id " + id + " non trovato!");
		}
	}

	public void update(Mezzo p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(p);
		transaction.commit();
		log.info("Mezzo con id " + p.getId() + " aggiornato!");
	}
}
