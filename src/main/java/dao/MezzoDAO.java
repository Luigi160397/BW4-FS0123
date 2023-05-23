package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entities.Mezzo;
import entities.StatoMezzo;
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

	public StatoMezzo getStatoMezzoById(String id) {
		TypedQuery<StatoMezzo> query = em.createNamedQuery("getStatoMezzo", StatoMezzo.class);
		query.setParameter("id", UUID.fromString(id));
		return query.getSingleResult();
	}

	public int getBigliettiVidimatiMezzoById(String id) {
		TypedQuery<Integer> query = em.createNamedQuery("getBigliettiVidimatiMezzo", Integer.class);
		query.setParameter("id", UUID.fromString(id));
		return query.getSingleResult();
	}

	public void getNumeroBigliettiVidimatiInPeriodoTempo(LocalDate data1, LocalDate data2) {
		TypedQuery<Integer> query = em.createNamedQuery("getBigliettiVidimati", Integer.class);
		query.setParameter("data1", data1);
		query.setParameter("data2", data2);
		Integer result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			result = 0;
		}
		log.info("Il numero di biglietti vidimati in questo intervallo è: " + String.valueOf(result));
	}

	public int getNumeroVolteTappaPercorsa(String id) {
		TypedQuery<Integer> query = em.createNamedQuery("getNumeroPassaggiTappa", Integer.class);
		query.setParameter("id", UUID.fromString(id));
		return query.getSingleResult();
	}
}
