package app;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoreDAO;
import dao.MezzoDAO;
import dao.TappaDAO;
import dao.TesseraDAO;
import dao.TicketDAO;
import dao.TrattaDAO;
import entities.Abbonamento;
import entities.Biglietto;
import entities.Bus;
import entities.Distributore;
import entities.StatoDistributore;
import entities.StatoMezzo;
import entities.Tappa;
import entities.Tessera;
import entities.Ticket;
import entities.TipoAbbonamento;
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
		Tessera tessera2 = new Tessera("Aldo", "Baglio", LocalDate.of(1968, 7, 17), LocalDate.of(2023, 4, 12));

		Distributore distributore1 = new Distributore(TipoDistributore.AUTOMATICO, StatoDistributore.ATTIVO);

		Tappa tappa1 = new Tappa("Colosseo", "Fori Imperiali", 5);
		Tappa tappa2 = new Tappa("Colosseo", "Cavour", 7);

		Tratta tratta1 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa1, 0.30);
		Tratta tratta2 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa2, 0.30);

		Bus bus1 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 15, tratta1);
		Bus bus2 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 35, tratta2);

		Biglietto biglietto1 = new Biglietto(tessera1, LocalDate.of(2023, 10, 15), distributore1,
				LocalDate.of(2023, 10, 16), bus1);

		Abbonamento abbonamento1 = new Abbonamento(tessera2, LocalDate.of(2023, 7, 16), distributore1,
				LocalDate.of(2023, 7, 17), bus2, TipoAbbonamento.MENSILE);

//		tessDao.save(tessera1);
//		tessDao.save(tessera2);
//
//		distDao.save(distributore1);
//		distDao.save(distributore1);
//
//		tappaDao.save(tappa1);
//		tappaDao.save(tappa2);
//
//		trattaDao.save(tratta1);
//		trattaDao.save(tratta2);
//
//		mezzoDao.save(bus1);
//		mezzoDao.save(bus2);
//
//		ticDao.save(biglietto1);
//		ticDao.save(abbonamento1);
//
//		tratta1.setMezzo(bus1);
//		tratta2.setMezzo(bus2);
//
//		trattaDao.update(tratta1);
//		trattaDao.update(tratta2);

		System.out.println();

		List<Ticket> trovatiPerPeriodoInTot = ticDao.getTotalTicket(LocalDate.of(2023, 6, 1),
				LocalDate.of(2023, 11, 2));
		if (trovatiPerPeriodoInTot.size() > 0) {
			log.info("--------------------- Ticket Trovati range di tempo ---------------------");
			trovatiPerPeriodoInTot.stream().forEach(t -> log.info(t.toString()));
			;
		} else {
			log.info("Nessun Ticket trovato per l'intervallo di tempo inserito");
		}

		System.out.println();

		List<Ticket> trovatiPerPeriodoEPuntoEmissione = ticDao.getTotalTicketByEmissionPoint(LocalDate.of(2023, 6, 1),
				LocalDate.of(2023, 8, 18), TipoDistributore.AUTOMATICO);
		if (trovatiPerPeriodoEPuntoEmissione.size() > 0) {
			log.info("--------------------- Ticket Trovati range di tempo e Distributore ---------------------");
			trovatiPerPeriodoEPuntoEmissione.stream().forEach(t -> log.info(t.toString()));
			;
		} else {
			log.info("Nessun Ticket trovato per l'intervallo di tempo e distributore inseriti");
		}

		System.out.println();

		List<Ticket> trovatiAbbonamentiValidi = ticDao.getAbbonamentiValidiPerNumeroTessera(
				"550ec839-77b8-4a2b-840b-d9f21a5f08ce", LocalDate.of(2023, 8, 13));
		if (trovatiAbbonamentiValidi.size() > 0) {
			log.info("--------------------- Ticket Validi Trovati Per Numero Di Tessera ---------------------");
			trovatiAbbonamentiValidi.stream().forEach(t -> log.info(t.toString()));
			;
		} else {
			log.info("Nessun Ticket valido trovato per il numero di tessera inserito");
		}

		System.out.println();

		StatoMezzo trovatoStatoMezzoPerId = mezzoDao.getStatoMezzoById("47f89c4b-3b3d-4348-b877-8a8607e3f2d1");
		if (trovatoStatoMezzoPerId != null) {
			log.info("--------------------- Stato Mezzo Trovato Per Id ---------------------");
			log.info("" + trovatoStatoMezzoPerId);
		} else {
			log.info("Nessun stato mezzo trovato per l'Id inserito");
		}

		System.out.println();

		int trovatiNumeroBigliettiVidimatiPerId = mezzoDao
				.getBigliettiVidimatiMezzoById("47f89c4b-3b3d-4348-b877-8a8607e3f2d1");
		if (trovatiNumeroBigliettiVidimatiPerId >= 0) {
			log.info("--------------------- Bigletti Vidimati Trovati Per Id ---------------------");
			log.info("" + trovatiNumeroBigliettiVidimatiPerId);
		} else {
			log.info("Nessun biglietto vidimato trovato per l'Id inserito");
		}

		System.out.println();

		em.close();
		emf.close();
	}

}
