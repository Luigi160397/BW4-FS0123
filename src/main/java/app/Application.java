package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
import entities.Tram;
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
		Tessera tessera3 = new Tessera("Ajeje", "Brazorf", LocalDate.of(1978, 6, 17), LocalDate.of(2023, 4, 15));

		Distributore distributore1 = new Distributore(TipoDistributore.AUTOMATICO, StatoDistributore.ATTIVO);
		Distributore distributore2 = new Distributore(TipoDistributore.FISICO, null);

		Tappa tappa1 = new Tappa("Colosseo", "Fori Imperiali", 5);
		Tappa tappa2 = new Tappa("Colosseo", "Cavour", 7);
		Tappa tappa3 = new Tappa("Colosseo", "Stadio Olimpico", 10);

		Tratta tratta1 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa1, 0.30);
		Tratta tratta2 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa2, 0.30);
		Tratta tratta3 = new Tratta(null, "Colosseo", "Termini", 0.50, tappa3, 0.40);

		Bus bus1 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 15, tratta1);
		Bus bus2 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 35, tratta2);
		Tram tram1 = new Tram(80, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 27, tratta3);

		Biglietto biglietto1 = new Biglietto(tessera1, LocalDate.of(2023, 10, 15), distributore1,
				LocalDate.of(2023, 10, 16), bus1);
		Biglietto biglietto2 = new Biglietto(tessera3, LocalDate.of(2023, 10, 15), distributore2,
				LocalDate.of(2023, 10, 16), tram1);

		Abbonamento abbonamento1 = new Abbonamento(tessera2, LocalDate.of(2023, 7, 16), distributore1,
				LocalDate.of(2023, 7, 17), bus2, TipoAbbonamento.MENSILE);

//		tessDao.save(tessera1);
//		tessDao.save(tessera2);
//		tessDao.save(tessera3);
//		distDao.save(distributore1);
//		distDao.save(distributore1);
//		distDao.save(distributore2);
//		tappaDao.save(tappa1);
//		tappaDao.save(tappa2);
//		tappaDao.save(tappa3);
//		trattaDao.save(tratta1);
//		trattaDao.save(tratta2);
//		trattaDao.save(tratta3);
//		mezzoDao.save(bus1);
//		mezzoDao.save(bus2);
//		mezzoDao.save(tram1);
//		ticDao.save(biglietto1);
//		ticDao.save(abbonamento1);
//		ticDao.save(biglietto2);
//		tratta1.setMezzo(bus1);
//		tratta2.setMezzo(bus2);
//		tratta3.setMezzo(tram1);
//		trattaDao.update(tratta1);
//		trattaDao.update(tratta2);
//		trattaDao.update(tratta3);

		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		int scelta = -1;
		System.out.println();
		System.out.println(Colors.ANSI_CYAN
				+ "-------------------------- Gestionale azienda di Trasporti --------------------------");
		System.out.println();

		System.out.println(Colors.ANSI_GREEN + "Scegli cosa vuoi controllare: (premi 0 per uscire)");
		System.out.println();

		System.out.println(Colors.ANSI_GREEN + "1. Trova biglietti emessi in un range di tempo");
		System.out.println(
				Colors.ANSI_GREEN + "2. Trova biglietti emessi per un range di tempo e per punto di emissione");
		System.out.println(Colors.ANSI_GREEN + "3. Trova biglietti validi per numero di tessera");
		System.out.println(Colors.ANSI_GREEN + "4. Trova lo stato del mezzo tramite ID");
		System.out.println(Colors.ANSI_GREEN + "5. Trova numero di biglietti vidimati in un mezzo");
		System.out.println(Colors.ANSI_GREEN + "6. Trova biglietti vidimati in un mezzo in un periodo di tempo");
		System.out.println(Colors.ANSI_GREEN + "7. Trova il numero di volte che una tappa è stata percorsa");
		System.out.println(Colors.ANSI_GREEN + "8. Trova il tempo effettivo di percorrenza di una tappa");
		while (scelta != 0) {

			try {
				scelta = scanner.nextInt();

				switch (scelta) {
				case 1:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Scegli un periodo di tempo");

					System.out.println(Colors.ANSI_GREEN + "Inserisci la prima data (formato: yyyy-MM-dd):");
					String input = scanner.nextLine();
					LocalDate data1 = LocalDate.parse(input, formatter);
					System.out.println();
					System.out.println(Colors.ANSI_GREEN + "Inserisci la seconda data (formato: yyyy-MM-dd):");
					String input2 = scanner.nextLine();
					LocalDate data2 = LocalDate.parse(input2, formatter);

					System.out.println();

					List<Ticket> trovatiPerPeriodoInTot = ticDao.getTotalTicket(data1, data2);
					log.info("--------------------- Ticket Trovati range di tempo ---------------------");
					if (trovatiPerPeriodoInTot.size() > 0) {
						trovatiPerPeriodoInTot.stream().forEach(t -> log.info(t.toString()));
						;
					} else {
						log.info("Nessun Ticket trovato per l'intervallo di tempo inserito");
					}
					break;
				case 2:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci il range di tempo e il punto di emissione per trovare i biglietti emessi");
					System.out.println(Colors.ANSI_GREEN + "Inserisci la prima data (formato: yyyy-MM-dd):");
					String input3 = scanner.nextLine();
					LocalDate data3 = LocalDate.parse(input3, formatter);
					System.out.println();
					System.out.println(Colors.ANSI_GREEN + "Inserisci la seconda data (formato: yyyy-MM-dd):");
					String input4 = scanner.nextLine();
					LocalDate data4 = LocalDate.parse(input4, formatter);
					System.out.println(Colors.ANSI_GREEN + "Inserisci il Tipo di Distributore (Automatico/Fisico):");
					String input5 = scanner.nextLine();

					while (!input5.equalsIgnoreCase("automatico") && !input5.equalsIgnoreCase("fisico")) {
						System.out.println(Colors.ANSI_RED
								+ "Tipo Distributore Inesistente! Reinserisci il Tipo di Distributore (Automatico/Fisico):");
						input5 = scanner.nextLine();
					}
					TipoDistributore tipo = TipoDistributore.valueOf(input5.toUpperCase());
					System.out.println();

					List<Ticket> trovatiPerPeriodoEPuntoEmissione = ticDao.getTotalTicketByEmissionPoint(data3, data4,
							tipo);
					log.info(
							"--------------------- Ticket Trovati range di tempo e Distributore ---------------------");
					if (trovatiPerPeriodoEPuntoEmissione.size() > 0) {
						trovatiPerPeriodoEPuntoEmissione.stream().forEach(t -> log.info(t.toString()));
						;
					} else {
						log.info("Nessun Ticket trovato per l'intervallo di tempo e distributore inseriti");
					}
					break;
				case 3:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci il numero di tessera e la data corrente per trovare i biglietti validi");
					System.out.println(Colors.ANSI_GREEN + "Inserisci il numero di tessera utente:");

					String tessera = scanner.nextLine();

					System.out.println(Colors.ANSI_GREEN + "Inserisci la data corrente:");

					String input6 = scanner.nextLine();
					LocalDate data5 = LocalDate.parse(input6, formatter);

					List<Ticket> trovatiAbbonamentiValidi = ticDao.getAbbonamentiValidiPerNumeroTessera(tessera, data5);
					log.info("--------------------- Ticket Validi Trovati Per Numero Di Tessera ---------------------");
					if (trovatiAbbonamentiValidi.size() > 0) {
						trovatiAbbonamentiValidi.stream().forEach(t -> log.info(t.toString()));
						;
					} else {
						log.info("Nessun Ticket valido trovato per il numero di tessera inserito");
					}

					break;
				case 4:
					scanner.nextLine();
					System.out
							.println(Colors.ANSI_GREEN + "Inserisci l'Id del mezzo per trovare il suo stato attività:");
					String mezzoId = scanner.nextLine();
					try {
						StatoMezzo trovatoStatoMezzoPerId = mezzoDao.getStatoMezzoById(mezzoId);
						log.info("" + trovatoStatoMezzoPerId);
					} catch (Exception e) {
						log.info("Nessun stato mezzo trovato per l'Id inserito");
					}
					break;
				case 5:
					scanner.nextLine();
					System.out
							.println(Colors.ANSI_GREEN + "Inserisci l'Id del mezzo per trovare i biglietti vidimati:");
					String mezzoId2 = scanner.nextLine();
					try {
						int trovatiNumeroBigliettiVidimatiPerId = mezzoDao.getBigliettiVidimatiMezzoById(mezzoId2);
						log.info("" + trovatiNumeroBigliettiVidimatiPerId);

					} catch (Exception e) {
						log.info("Nessun biglietto vidimato trovato per l'Id inserito");
					}
					break;
				case 6:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci l'intervallo di tempo entro cui cercare i Biglietti Vidimati");
					System.out.println(Colors.ANSI_GREEN + "Inserisci la prima data (formato: yyyy-MM-dd):");
					String input7 = scanner.nextLine();
					LocalDate data6 = LocalDate.parse(input7, formatter);
					System.out.println(Colors.ANSI_GREEN + "Inserisci la seconda data (formato: yyyy-MM-dd):");
					String input8 = scanner.nextLine();
					LocalDate data7 = LocalDate.parse(input8, formatter);
					log.info(
							"--------------------- Bigletti Vidimati Trovati dato un periodo di tempo ---------------------");
					try {
						Long bigliettiTrovatiPerRangeTempo = ticDao.getNumeroBigliettiVidimatiInPeriodoTempo(data6,
								data7);
						log.info("" + bigliettiTrovatiPerRangeTempo);
					} catch (Exception e) {
						log.info("Nessun biglietto vidimato trovato per il periodo inserito");
					}
					break;
				case 7:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci id del mezzo per vedere quante volte è stata percorsa la tappa a lui associata");
					String trattaId = scanner.nextLine();

					log.info(
							"--------------------- Numero di Volte Che è stata Percorsa la Tappa ---------------------");

					try {
						int numeroPassaggiPerTappa = mezzoDao.getNumeroVolteTappaPercorsa(trattaId);
						log.info("" + numeroPassaggiPerTappa);
					} catch (Exception e) {
						log.info("Nessun passaggio trovato per questo ID");
					}

					break;
				case 8:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci l'Id della tratta per controllare il tempo effettivo di percorrenza della sua tappa:");
					String trattaId2 = scanner.nextLine();
					log.info("--------------------- Tempo Effettivo Di Percorrenza Tappa ---------------------");

					try {
						double tempoPercorrenzaTappa = trattaDao.getTempoEffettivoPercorrenza(trattaId2);
						log.info("" + tempoPercorrenzaTappa + " ore");

					} catch (Exception e) {
						log.info("Nessuna Tratta trovata con questo Id");
					}

					break;
				case 0:
					System.out.println(
							Colors.ANSI_GREEN + "Arrivederci, grazie di aver utilizzato la nostra applicazione!");
					break;
				default:
					System.out.println(Colors.ANSI_RED + "Opzione non valida. Riprova.");
				}
			} catch (InputMismatchException e) {
				System.out.println(
						Colors.ANSI_RED + "Non sono ammessi valori testuali. Inserisci un numero compreso tra 1 e 8");
				scanner.nextLine();
			}
		}

		scanner.close();
		em.close();
		emf.close();
	}

}
