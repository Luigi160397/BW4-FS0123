package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Ticket;
import entities.TipoDistributore;
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

	public Ticket getById(UUID id) {

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

	public void update(Ticket p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(p);
		transaction.commit();
		log.info("Ticket con id " + p.getId() + " aggiornato!");
	}

	public List<Ticket> getTotalTicket(LocalDate start, LocalDate end) {
		TypedQuery<Ticket> query = em.createNamedQuery("findBigliettiEmessiDatoUnPeriodoInTotale", Ticket.class);
		query.setParameter("start", start);
		query.setParameter("end", end);
		return query.getResultList();
	}

	public List<Ticket> getTotalTicketByEmissionPoint(LocalDate start, LocalDate end, TipoDistributore tipo) {
		TypedQuery<Ticket> query = em.createNamedQuery("findBigliettiEmessiDatoUnPeriodoPerPuntoEmissione",
				Ticket.class);
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("distributore", tipo);
		return query.getResultList();
	}

	public List<Ticket> getAbbonamentiValidiPerNumeroTessera(String numeroTessera, LocalDate data) {
		TypedQuery<Ticket> query = em.createNamedQuery("findAbbonamentiValidiPerNumeroTessera", Ticket.class);
		query.setParameter("tessera", UUID.fromString(numeroTessera));
		query.setParameter("data", data);

		return query.getResultList();
	}

	public Long getNumeroBigliettiVidimatiInPeriodoTempo(LocalDate data1, LocalDate data2) {
		TypedQuery<Long> query = em.createNamedQuery("getBigliettiVidimati", Long.class);
		query.setParameter("data1", data1);
		query.setParameter("data2", data2);

		return query.getSingleResult();

	}
}
