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
import entities.Biglietto;
import entities.Bus;
import entities.Distributore;
import entities.StatoDistributore;
import entities.StatoMezzo;
import entities.Tappa;
import entities.Tessera;
import entities.TipoDistributore;
import entities.Tratta;
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
		Distributore distributore1 = new Distributore(TipoDistributore.AUTOMATICO, StatoDistributore.ATTIVO);
		Tappa tappa1 = new Tappa("Colosseo", "Fori Imperiali", 5);
		Tratta tratta1 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa1, 0.30);
		Bus bus1 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 15, tratta1);
		Biglietto biglietto1 = new Biglietto(tessera1, LocalDate.of(2023, 10, 15), distributore1,
				LocalDate.of(2023, 10, 16), bus1);

		tessDao.save(tessera1);
		distDao.save(distributore1);
		tappaDao.save(tappa1);
		trattaDao.save(tratta1);
		mezzoDao.save(bus1);
		ticDao.save(biglietto1);
		tratta1.setMezzo(bus1);
		trattaDao.update(tratta1);

		em.close();
		emf.close();
	}

}
