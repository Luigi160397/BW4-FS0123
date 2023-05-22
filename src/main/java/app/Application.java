package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoreDAO;
import dao.MezzoDAO;
import dao.TappaDAO;
import dao.TesseraDAO;
import dao.TicketDAO;
import dao.TrattaDAO;
import entities.Tessera;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		DistributoreDAO distDao = new DistributoreDAO(em);
		MezzoDAO mezzoDao = new MezzoDAO(em);
		TappaDAO tappaDao = new TappaDAO(em);
		TesseraDAO tessDao = new TesseraDAO(em);
		TicketDAO ticDao = new TicketDAO(em);
		TrattaDAO trattaDao = new TrattaDAO(em);

		Tessera tessera1 = new Tessera("Paolo", "Bitta", LocalDate.of(1975, 3, 23), LocalDate.of(2023, 3, 10));
//		Biglietto biglietto1 = new Biglietto(tessera1,LocalDate.of(2023, 10, 15), );

		em.close();
		emf.close();
	}

}
