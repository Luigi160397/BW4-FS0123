package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Tessera;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TesseraDAO {
	private final EntityManager em;

	public TesseraDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tessera e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Tessera salvata!");

	}

	public Tessera getByISBN(UUID id) {

		Tessera found = em.find(Tessera.class, id);
		return found;
	}

	public void delete(UUID id) {
		Tessera found = em.find(Tessera.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Tessera con id " + id + " eliminata!");
		} else {
			log.info("Tessera con id " + id + " non trovata!");
		}
	}

	public void update(Tessera p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(p);
		transaction.commit();
		log.info("Tessera con numero tessera " + p.getNumeroTessera() + " aggiornata!");
	}
}
