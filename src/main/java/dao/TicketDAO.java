package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Ticket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketDAO {
	private final EntityManager em;

	public TicketDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Ticket e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Ticket salvato!");

	}

	public Ticket getByISBN(UUID id) {

		Ticket found = em.find(Ticket.class, id);
		return found;
	}

	public void delete(UUID id) {
		Ticket found = em.find(Ticket.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Ticket con id " + id + " eliminato!");
		} else {
			log.info("Ticket con id " + id + " non trovato!");
		}
	}
}
